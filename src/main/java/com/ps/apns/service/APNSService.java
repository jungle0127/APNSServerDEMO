package com.ps.apns.service;

import com.ps.apns.ApnsClientManager;
import com.ps.apns.ApnsClientSingleton;
import com.ps.apns.dao.service.PushlogService;
import com.ps.apns.dao.service.TokenService;
import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;
import com.turo.pushy.apns.util.concurrent.PushNotificationFuture;
import com.turo.pushy.apns.util.concurrent.PushNotificationResponseListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("apnsService")
public class APNSService {
	private static final Logger logger = LoggerFactory.getLogger(APNSService.class);
	
	@Value("${apple.tlsauth.p12filepath}")
	private String p12FilePath;
	@Value("${apple.tlsauth.p12filepwd}")
	private String p12Pwd;
	@Value("${apple.tokenauth.p8filepath}")	
	private String p8FilePath;
	@Value("${apple.tokenauth.teamid}")
	private String teamId;
	@Value("${apple.tokenauth.keyid}")
	private String keyId;
	
	@Value("${apple.topic}")
	// topic is generally the bundle ID of the receiving app
	private String topic;

	
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private PushlogService pushlogService;
	// TODO
	private ApnsClient apnsClient;

	public APNSService() {

	}

	public void sendNotification() {
//		this.apnsClient = ApnsClientSingleton.getInstance(this.p12FilePath, this.p12Pwd);
		this.apnsClient = ApnsClientManager.getInstance(this.p8FilePath, this.teamId, this.keyId);
		if(apnsClient != null) {
			logger.info("Got APNs client instance.");
		}
		else {
			logger.warn("APNs client is null.");
			return;
		}
		String tokenStr = tokenService.getToken(1L).getToken();
		logger.info("Got device token:{} from DB", tokenStr);
		final SimpleApnsPushNotification pushNotification;
		final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
		payloadBuilder.setAlertBody("Example!");
		payloadBuilder.setAlertTitle("DEMO Notification");
		
		final String payload = payloadBuilder.buildWithDefaultMaximumLength();
		final String token = TokenUtil.sanitizeTokenString(tokenStr);
		pushNotification = new SimpleApnsPushNotification(token, this.topic, payload);
		final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>> sendNotificationFuture = apnsClient
				.sendNotification(pushNotification);
		sendNotificationFuture.addListener(new PushNotificationResponseListener<SimpleApnsPushNotification>() {

			@Override
			public void operationComplete(
					final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>> future)
					throws Exception {
				// When using a listener, callers should check for a failure to send a
				// notification by checking whether the future itself was successful
				// since an exception will not be thrown.
				if (future.isSuccess()) {
					final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse = sendNotificationFuture
							.getNow();

					// Handle the push notification response as before from here.
					// final PushNotificationResponse<SimpleApnsPushNotification>
					// pushNotificationResponse =
					// sendNotificationFuture.get();

					if (pushNotificationResponse.isAccepted()) {
						System.out.println("Push notification accepted by APNs gateway.");
						logger.info("Push notification accepted by APNs gateway.");
					} else {
						System.out.println("Notification rejected by the APNs gateway: "
								+ pushNotificationResponse.getRejectionReason());
						logger.warn("Notification rejected by the APNs gateway: "
								+ pushNotificationResponse.getRejectionReason());

						if (pushNotificationResponse.getTokenInvalidationTimestamp() != null) {
							System.out.println("\t…and the token is invalid as of "
									+ pushNotificationResponse.getTokenInvalidationTimestamp());
							logger.warn("\t…and the token is invalid as of "
									+ pushNotificationResponse.getTokenInvalidationTimestamp());
						}
					}
				} else {
					// Something went wrong when trying to send the notification to the
					// APNs gateway. We can find the exception that caused the failure
					// by getting future.cause().
					future.cause().printStackTrace();
					logger.error(future.cause().getMessage());
				}
			}
		});
	}
}
