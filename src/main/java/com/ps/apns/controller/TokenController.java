package com.ps.apns.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apns")
public class TokenController {
    @PostMapping("/token")
    public String receiveToken(){
        return "1";
    }
    @GetMapping("/token")
    public String receiveTokenInvalidREST(){
        return "1";
    }
}
