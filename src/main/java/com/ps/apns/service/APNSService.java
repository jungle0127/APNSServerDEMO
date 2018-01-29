package com.ps.apns.service;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;

import java.io.File;
import java.io.IOException;

public class APNSService {
    public void singleNotification() throws IOException{
        ApnsClient apnsClient = new ApnsClientBuilder().setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
                .setClientCredentials(new File("/path/certificate.p12"),"p12Password")
                .build();
    }
    public void sendNotification(){
        final SimpleApnsPushNotification pushNotification;
        {
            final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
            payloadBuilder.setAlertBody("Example!");

            final String payload = payloadBuilder.buildWithDefaultMaximumLength();
            final String token = TokenUtil.sanitizeTokenString("<efc7492 bdbd8209>");
            pushNotification = new SimpleApnsPushNotification(token, "com.example.myApp", payload);
        }

    }
}
