package com.ps.apns.dao.service;

import com.ps.apns.dao.domain.TokenMapper;
import com.ps.apns.dao.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tokenService")
public class TokenService {
    @Autowired
    private TokenMapper tokenMapper;

    public  void addToken(String token){
        // b606c95c4b3956de89d67ecfa02811ebc5d1a09c6f832c3be67b1d9554a66683
        Token pojo = new Token();
        pojo.setToken(token);
        pojo.setActive(Byte.valueOf("1"));
        this.tokenMapper.insert(pojo);
    }

    public Token getToken(Long id){
        return this.tokenMapper.selectByPrimaryKey(1L);
    }
}
