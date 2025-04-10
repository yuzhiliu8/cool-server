package com.coolserver.server.storage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolserver.server.auth.APIResponse;
import com.coolserver.server.auth.AuthService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/cool-server/api/storage")
public class StorageController {

    private final AuthService authService;
    private final StorageService storageService;

    public StorageController(AuthService authService, StorageService storageService){
        this.storageService = storageService;
        this.authService = authService;
    }

    @GetMapping("/get-all-items")
    public ResponseEntity<APIResponse<List<StorageItem>>> getAllStorageItemsByUID(
        @CookieValue(name = "sessionId", defaultValue = "-1") String cookie) {
        
        boolean val = authService.validateSession(Long.parseLong(cookie));
        if (!val){
            APIResponse<List<StorageItem>> body = APIResponse.UnauthorizedResponse();
            return ResponseEntity.status(401).body(body);
        }

        APIResponse<List<StorageItem>> body = APIResponse.OK(storageService.getStorageItemsByUserId(Long.parseLong(cookie)));
        return ResponseEntity.ok(body);
        
    }
    

    @PostMapping("/upload")
    public void uploadItems(){

    }

}
