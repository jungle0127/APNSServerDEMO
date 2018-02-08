package com.ps.apns.controller;


import com.ps.apns.dao.service.TokenService;
import com.ps.apns.dto.RestResponse;

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
    public RestResponse<String> receiveToken( @RequestBody String deviceId){
    	logger.info("DeviceID {} will be added.", deviceId);
        this.tokenService.addToken(deviceId);
        RestResponse<String> resp = new RestResponse<>();
        resp.setCode(1);
        resp.setData(deviceId);
        resp.setMessage("Success");
        return resp;
    }
}
