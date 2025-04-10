package com.coolserver.server.minio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.minio.MinioClient;

@Configuration
public class MinioClientConfig {

    @Autowired
    private Environment environment;
    
    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient = MinioClient.builder()
            .endpoint(environment.getProperty("MC_ENDPOINT"))
            .credentials(environment.getProperty("MC_ACCESS_KEY"), environment.getProperty("MC_SECRET_KEY"))
            .build();
        
        System.out.println("minio bean instantiated");
        return minioClient;
    }
}
