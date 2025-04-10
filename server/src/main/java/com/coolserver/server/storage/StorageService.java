package com.coolserver.server.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.coolserver.server.auth.APIResponse;
import com.coolserver.server.auth.AuthService;

import io.minio.MinioClient;

@Service
public class StorageService {

    private final AuthService authService;
    private final StorageItemRepository storageItemRepository; 
    private final MinioClient minio;

    public StorageService(AuthService authService, StorageItemRepository storageItemRepository, MinioClient minio){
        this.authService = authService;
        this.storageItemRepository = storageItemRepository;
        this.minio = minio;
    }


    public List<StorageItem> getStorageItemsByUserId(Long id){
        return storageItemRepository.findAllByUserId(id);
    }

    public List<StorageItem> uploadStorageItems(@RequestParam MultipartFile[] files, Long userId){

        List<StorageItem> items = new ArrayList<StorageItem>();
        for (int i = 0; i < files.length; i++){
            MultipartFile file = files[i];
            String name = file.getName();
            StorageItem item = new StorageItem(name, LocalDateTime.now(), file.getSize(), "", userId);
            items.add(item);
        }

        return items;    
    }

    
}
