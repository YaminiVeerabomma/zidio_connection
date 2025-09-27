package com.example.controller;

import com.example.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisService redisService;

    // Test Redis connection
    @GetMapping("/ping")
    public String testPing() {
        String pong = redisService.ping();
        return "Redis ping response: " + pong;
    }

    // Test setting a value
    @PostMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return "Key '" + key + "' set with value '" + value + "'";
    }

    // Test getting a value
    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        String value = redisService.getValue(key);
        return value != null ? "Value: " + value : "Key '" + key + "' not found";
    }
}
