package com.coolserver.server.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/cool-server/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }
    
    @GetMapping
    public String home(){
        return "Auth";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam String email, @RequestParam String password, HttpServletResponse response){
        ResponseEntity<AuthResponse> authResponse = authService.authenticateUser(email, password);

        if (!authResponse.getBody().isSuccess()){
            return authResponse;
        }

        int cookieLength = 60 * 60 * 24; //1 day
        Cookie sessionCookie = new Cookie("sessionId", authResponse.getBody().getSession().getSessionId().toString());
        sessionCookie.setMaxAge(cookieLength);
        sessionCookie.setSecure(true);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setPath("/");

        response.addCookie(sessionCookie);
        return authResponse;
    }
}
