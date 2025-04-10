package com.coolserver.server.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coolserver.server.global.AppConstants;
import com.coolserver.server.session.Session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
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
    public ResponseEntity<APIResponse<Session>> login(@RequestParam String email, @RequestParam String password, HttpServletResponse response){
        ResponseEntity<APIResponse<Session>> apiResponse= authService.authenticateUser(email, password);

        if (!apiResponse.getBody().isSuccess()){
            return apiResponse;
        }

        int cookieLength = 60 * 60 * 24 * AppConstants.SESSION_LENGTH;

        Cookie sessionCookie = new Cookie("sessionId", apiResponse.getBody().getData().getSessionId().toString());
        sessionCookie.setMaxAge(cookieLength);
        sessionCookie.setSecure(true);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setPath("/");

        response.addCookie(sessionCookie);
        return apiResponse;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<APIResponse<Session>> authenticateSession(@CookieValue(value = "sessionId", defaultValue = "-1") String sessionCookie){
        boolean validate = authService.validateSession(Long.parseLong(sessionCookie));

        if (sessionCookie.equals("-1") || validate == false){
            APIResponse<Session> apiResponse = new APIResponse<Session>(false, "401 Session Unauthorized", null);
            return ResponseEntity.status(401).body(apiResponse);
        }

        try {
            Session session = authService.getSessionById(Long.parseLong(sessionCookie));
            APIResponse<Session> apiResponse = new APIResponse<Session>(true, "200 OK session good", session);
            return ResponseEntity.ok(apiResponse);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(401).body(new APIResponse<Session>(false, "401 Session unauthorized", null));
        } 
    }
}
