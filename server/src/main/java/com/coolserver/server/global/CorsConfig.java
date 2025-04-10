package com.coolserver.server.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Autowired
    private Environment environment;

    @Bean
    WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry){
                // System.out.println(environment.getProperty("CORS_ALLOWED_ORIGIN"));
                corsRegistry.addMapping("/**")
                .allowedOrigins(environment.getProperty("CORS_ALLOWED_ORIGIN"))
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
            }
        };
    } 
}
