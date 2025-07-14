package com.example.shutdown;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.DiscoveryClient;

@Component
public class EurekaShutdownHandler {

    @Autowired
    private EurekaClient netflixEurekaClient;

    @PreDestroy
    public void shutdown() {
        System.out.println(">>> Deregistering from Eureka...");
        if (netflixEurekaClient instanceof DiscoveryClient) {
            ((DiscoveryClient) netflixEurekaClient).shutdown();
        } else {
            System.out.println(">>> EurekaClient is not an instance of DiscoveryClient");
        }
    }
}
