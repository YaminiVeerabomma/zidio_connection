package com.example.zidio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
public class ApiGateWayApplication {
	
	public static void main(String[] ars) {
		SpringApplication.run(ApiGateWayApplication.class, ars);
	}

}