package com.ps.apns;

/**
 * Creating a single APNs client instance for each APNs certificate/key,
 * and keeping the APNs client around for the lifetime of this application.
 */
public class ApnsClientManager {
    private String pcks12File;
    private String pcks12FilePassword;

    private String pcks8File;
    private String keyId;
    private String teamId;

    private ApnsClientManager(String pcks12File,String pcks12FilePassword){
        this.pcks12File = pcks12File;
        this.pcks12FilePassword = pcks12FilePassword;
    }

    private ApnsClientManager(String pcks8File, String keyId, String teamId){
        this.pcks8File = pcks8File;
        this.teamId = teamId;
        this.keyId = keyId;
    }

    private ApnsClientManager(){

    }


}
