package com.coolserver.server.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/cool-server/api/auth")
public class AuthController {
    
    @GetMapping
    public String home(){
        return "Auth";
    }
}
