package com.ps.apns;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.auth.ApnsSigningKey;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creating a single APNs client instance for each APNs certificate/key,
 * and keeping the APNs client around for the lifetime of this application.
 */
public class ApnsClientManager {
	private static final Logger logger = LoggerFactory.getLogger(ApnsClientManager.class);
    private String pkcs12File;
    private String pkcs12FilePassword;

    private String pkcs8File;
    private String keyId;
    private String teamId;

    private static ConcurrentHashMap<String, ApnsClient> apnsClientHashMap = new ConcurrentHashMap<>();
    private ApnsClientManager(String pkcs12File,String pkcs12FilePassword){
        this.pkcs12File = pkcs12File;
        this.pkcs12FilePassword = pkcs12FilePassword;
    }

    private ApnsClientManager(String pkcs8File, String keyId, String teamId){
        this.pkcs8File = pkcs8File;
        this.teamId = teamId;
        this.keyId = keyId;
    }

    private ApnsClientManager(){

    }

    public synchronized static ApnsClient getInstance(String pkcs12File, String pkcs12FilePassword){
        if(apnsClientHashMap.containsKey(pkcs12File + pkcs12FilePassword)){
        	logger.debug("APNs client instance exists.");
            return apnsClientHashMap.get(pkcs12File + pkcs12FilePassword);
        }
        else{
            try {
                final ApnsClient apnsClient = new ApnsClientBuilder()
                        .setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
                        .setClientCredentials(new File(pkcs12File), pkcs12FilePassword)
                        .build();
                logger.debug("No APNs client exists, create a new one.");
                apnsClientHashMap.put(pkcs12File + pkcs12FilePassword, apnsClient);
                return apnsClient;
            } catch (IOException e) {
            	logger.error("Create APNs client failed with exception:" + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    public synchronized  static ApnsClient getInstance(String pkcs8File, String teamId, String keyId){
        if(apnsClientHashMap.containsKey(pkcs8File+teamId+keyId)){
        	logger.debug("APNs client exist");
            return apnsClientHashMap.get(pkcs8File+teamId+keyId);
        }
        else{
            try {
                final ApnsClient apnsClient = new ApnsClientBuilder()
                        .setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
                        .setSigningKey(ApnsSigningKey.loadFromPkcs8File(new File(pkcs8File),teamId, keyId))
                        .build();
                logger.debug("No APNs client exists, creat a new one.");
                apnsClientHashMap.put(pkcs8File + teamId + keyId, apnsClient);
                return apnsClient;
            } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
                e.printStackTrace();
                logger.error("Create ApnsClient failed with exception:{}", e.getMessage());
            }
        }
        return null;
    }

    public String getPkcs12File() {
        return pkcs12File;
    }

    public String getPkcs12FilePassword() {
        return pkcs12FilePassword;
    }

    public String getPkcs8File() {
        return pkcs8File;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getTeamId() {
        return teamId;
    }
}
