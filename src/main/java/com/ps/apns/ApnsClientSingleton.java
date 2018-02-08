package com.ps.apns;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;

import java.io.File;
import java.io.IOException;

public class ApnsClientSingleton {
    private static volatile ApnsClient apnsClient;
    private ApnsClientSingleton(){

    }

    public  static ApnsClient getInstance(String p12FilePath, String p12Pwd){
        if(apnsClient == null){
            synchronized (ApnsClient.class){
                if(apnsClient == null){
                    try {
                        apnsClient = new ApnsClientBuilder().setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
                                .setClientCredentials(new File(p12FilePath), p12Pwd)
                                .build();
                        
                        return apnsClient;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return apnsClient;
    }

}
