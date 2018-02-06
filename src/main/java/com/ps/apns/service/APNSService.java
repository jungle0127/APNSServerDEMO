package com.ps.apns.service;

import com.ps.apns.ApnsClientSingleton;
import com.ps.apns.dao.service.PushlogService;
import com.ps.apns.dao.service.TokenService;
import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;
import com.turo.pushy.apns.util.concurrent.PushNotificationFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service("apnsService")
public class APNSService {
    @Value("${apple.tlsauth.p12filepath}")
    private String p12FilePath;
    @Value("${apple.tlsauth.p12filepwd}")
    private String p12Pwd;
    @Value("${apple.topic")
    // topic is generally the bundle ID of the receiving app
    private String topic;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private PushlogService pushlogService;
    //TODO
    private ApnsClient apnsClient;// = ApnsClientSingleton.getInstance(this.p12FilePath,this.p12Pwd);
    public APNSService() {
    	
    }
    public void sendNotification(){
        String tokenStr = tokenService.getToken(1L).getToken();
        final SimpleApnsPushNotification pushNotification;
        final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
        payloadBuilder.setAlertBody("Example!");

        final String payload = payloadBuilder.buildWithDefaultMaximumLength();
        final String token = TokenUtil.sanitizeTokenString(tokenStr);
        pushNotification = new SimpleApnsPushNotification(token, this.topic, payload);
        final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>
                sendNotificationFuture = apnsClient.sendNotification(pushNotification);
       // sendNotificationFuture.addListener(new PushNotificationFuture<SimpleApnsPushNotification>(){

//        });
    }
}
