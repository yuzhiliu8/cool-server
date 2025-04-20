package com.coolserver.server.minio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

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

        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("storage-items").build());
            if (!found){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("storage-items").build());
            }
        } catch (ErrorResponseException | InvalidKeyException | InsufficientDataException | InternalException | InvalidResponseException
                | NoSuchAlgorithmException | ServerException | XmlParserException | IllegalArgumentException
                | IOException e) {
            e.printStackTrace();
        }

        return minioClient;
    }
}
