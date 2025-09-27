package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Check connection
    public String ping() {
        try {
            return redisTemplate.getConnectionFactory().getConnection().ping();
        } catch (Exception e) {
            return "Redis connection failed: " + e.getMessage();
        }
    }

    // Optional: Set a value
    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // Optional: Get a value
    public String getValue(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
