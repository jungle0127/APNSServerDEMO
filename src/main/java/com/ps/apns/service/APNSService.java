package com.ps.apns.service;

import com.ps.apns.ApnsClientSingleton;
import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;
import com.turo.pushy.apns.util.concurrent.PushNotificationFuture;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.concurrent.ExecutionException;


@Service("apnsService")
public class APNSService {
    private ApnsClient apnsClient = ApnsClientSingleton.getInstance("","");

    public void sendNotification(){

        final SimpleApnsPushNotification pushNotification;
        final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
        payloadBuilder.setAlertBody("Example!");

        final String payload = payloadBuilder.buildWithDefaultMaximumLength();
        final String token = TokenUtil.sanitizeTokenString("<efc7492 bdbd8209>");
        pushNotification = new SimpleApnsPushNotification(token, "com.example.myApp", payload);
        final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>
                sendNotificationFuture = apnsClient.sendNotification(pushNotification);
       // sendNotificationFuture.addListener(new PushNotificationFuture<SimpleApnsPushNotification>(){

//        });
    }
}
