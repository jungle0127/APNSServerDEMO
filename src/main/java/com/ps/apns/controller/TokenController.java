package com.ps.apns.controller;


import com.ps.apns.dao.domain.TokenMapper;
import com.ps.apns.dao.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apns")
public class TokenController {
    @Autowired
    private TokenService tokenService;
    @PostMapping("/token")
    public String receiveToken( @RequestBody String deviceId){
        this.tokenService.addToken(deviceId);
        return deviceId;
    }
}
