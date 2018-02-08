package com.ps.apns.controller;


import com.ps.apns.dao.service.TokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apns")
public class TokenController {
	private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
	
    @Autowired
    private TokenService tokenService;
    @PostMapping("/token")
    public String receiveToken( @RequestBody String deviceId){
    	logger.info("DeviceID {} will be added.", deviceId);
        this.tokenService.addToken(deviceId);
        return deviceId;
    }
}
