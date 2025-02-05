package com.coolserver.server.auth;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coolserver.server.user.User;
import com.coolserver.server.user.UserRepository;
import com.coolserver.server.util.Util;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> authenticateUser(String email, String password){
        Optional<User> Ouser = userRepository.findByEmail(email);
        if (!Ouser.isPresent()){
            return ResponseEntity.status(401).body("email is not registered");
        }

        User user = Ouser.get();
        String salt = user.getSalt();
        String hashedPwd = Util.getHashedPassword(password, salt);
        
        if (!user.getPassword().equals(hashedPwd)){
            return ResponseEntity.status(401).body("incorrect password");
        }

        return ResponseEntity.ok("authentication success");
    }
}
