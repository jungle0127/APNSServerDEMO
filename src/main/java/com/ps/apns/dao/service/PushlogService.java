package com.ps.apns.dao.service;

import com.ps.apns.dao.domain.PushlogMapper;
import com.ps.apns.dao.model.Pushlog;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushlogService {
    @Autowired
    private PushlogMapper pushlogMapper;

    public  void insertLog(String token, String content){
        Pushlog pojo = new Pushlog();
        pojo.setToken(token);
        pojo.setContent(content);
        pojo.setSendTime(new Date());
        this.pushlogMapper.insert(pojo);
    }
}
