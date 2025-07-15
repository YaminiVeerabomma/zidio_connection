package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	
	@Bean
	public Cloudinary cloudnary() {
		return new Cloudinary(ObjectUtils.asMap(
				"cloud_name","your_cloud_name",
				"api_key","your_api_key","api_secret",
				"our_api_secret","secure",true));
	}

}
