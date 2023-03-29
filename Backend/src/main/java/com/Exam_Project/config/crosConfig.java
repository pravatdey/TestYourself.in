package com.Exam_Project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 

@Configuration
public class crosConfig {
@Value("${allowed.origin}")
private String allowedOrigin;

@Bean
public WebMvcConfigurer getCorsConfigurer() {
	return new WebMvcConfigurer() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			 registry.addMapping("/**").allowedOrigins(allowedOrigin).allowedMethods("GET,POST,PUT,DELETE").allowedHeaders("*");
		}
	};
}
}
