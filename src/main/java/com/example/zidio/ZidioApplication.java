package com.example.zidio;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.annotation.EnableCaching;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	    info = @Info(
	        title = "Zidio Connect API",
	        version = "1.0",
	        description = "API documentation for Zidio Job Portal"
	    )
	)

@EnableCaching
@SpringBootApplication
@EnableEurekaServer
@ComponentScan(basePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.repository")
@EntityScan("com.example.entity")

public class ZidioApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZidioApplication.class, args);
    }
}



