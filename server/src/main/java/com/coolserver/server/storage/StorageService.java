package com.coolserver.server.storage;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Service
public class StorageService {

    private final StorageItemRepository storageItemRepository; 
    private final MinioClient minioClient;

    public StorageService(StorageItemRepository storageItemRepository, MinioClient minioClient){
        this.storageItemRepository = storageItemRepository;
        this.minioClient = minioClient;
    }


    public List<StorageItem> getStorageItemsByUserId(Long id){
        return storageItemRepository.findAllByUserId(id);
    }

    public List<StorageItem> uploadStorageItems(MultipartFile[] files, Long userId){

        List<StorageItem> items = new ArrayList<StorageItem>();
        for (int i = 0; i < files.length; i++){
            MultipartFile file = files[i];
            String fileName = file.getOriginalFilename();
            // System.out.println(fileName);
            StorageItem item = new StorageItem(fileName, LocalDateTime.now(), file.getSize(), file.getContentType(), userId);
            items.add(item);
            

            try {
                minioClient.putObject(
                    PutObjectArgs.builder().bucket("storage-items").object(userId + "/" + fileName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());

            } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
                    | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
                    | IllegalArgumentException | IOException e) {
                e.printStackTrace();
            }
        }

        storageItemRepository.saveAll(items);

        return items;    
    }

    
}
