package com.coolserver.server.auth;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coolserver.server.user.User;
import com.coolserver.server.user.UserRepository;
import com.coolserver.server.util.Util;

@Service
public class AuthService {

    private UserRepository userRepository;
    private AuthRepository authRepository;

    public AuthService(UserRepository userRepository, AuthRepository authRepository){
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    public ResponseEntity<String> authenticateUser(String email, String password){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email);
        authRequest.setTime(LocalTime.now());
        
        Optional<User> Ouser = userRepository.findByEmail(email);
        if (!Ouser.isPresent()){
            authRequest.setSuccess(false);
            authRepository.save(authRequest);
            return ResponseEntity.status(401).body("email is not registered");
        }

        User user = Ouser.get();
        String salt = user.getSalt();
        String hashedPwd = Util.getHashedPassword(password, salt);

        authRequest.setHash(hashedPwd);
        
        if (!user.getPassword().equals(hashedPwd)){
            authRequest.setSuccess(false);
            authRepository.save(authRequest);
            return ResponseEntity.status(401).body("incorrect password");
        }

        authRequest.setSuccess(true);
        authRepository.save(authRequest);
        return ResponseEntity.ok("authentication success");
    }
}
