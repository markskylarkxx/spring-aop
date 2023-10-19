package com.kceecodes.springaop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogService {

    // access logs from redis;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    // retrieve the log messages from the redis server
    public List<String> getLogs() {
        return redisTemplate.opsForList().range("log", 0, -1);
    }

}


