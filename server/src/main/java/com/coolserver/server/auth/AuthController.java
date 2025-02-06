package com.coolserver.server.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coolserver.server.session.Session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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
    public Session login(@RequestParam String email, @RequestParam String password, HttpServletResponse response){
        Session session = authService.authenticateUser(email, password);

        int cookieLength = 60 * 60 * 24; //1 day
        Cookie sessionCookie = new Cookie("sessionId", session.getSessionId().toString());
        sessionCookie.setMaxAge(cookieLength);
        sessionCookie.setSecure(true);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setPath("/");

        response.addCookie(sessionCookie);
        return session;
    }
}
