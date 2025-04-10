package com.coolserver.server.auth;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coolserver.server.session.Session;
import com.coolserver.server.session.SessionRepository;
import com.coolserver.server.user.User;
import com.coolserver.server.user.UserRepository;
import com.coolserver.server.util.Util;

@Service
public class AuthService {

    private UserRepository userRepository;
    private AuthRepository authRepository;
    private SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository, AuthRepository authRepository, SessionRepository sessionRepository){
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.sessionRepository = sessionRepository;
    }

    public ResponseEntity<APIResponse<Session>> authenticateUser(String email, String password){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email);
        authRequest.setTime(LocalDateTime.now());
        
        Optional<User> Ouser = userRepository.findByEmail(email);
        if (!Ouser.isPresent()){
            authRequest.setSuccess(false);
            authRepository.save(authRequest);
            return ResponseEntity.status(401).body(new APIResponse<Session>(false, "Incorrect email/password", null));
        }

        User user = Ouser.get();
        String salt = user.getSalt();
        String hashedPwd = Util.getHashedPassword(password, salt);

        authRequest.setHash(hashedPwd);
        
        if (!user.getPassword().equals(hashedPwd)){
            authRequest.setSuccess(false);
            authRepository.save(authRequest);
            return ResponseEntity.status(401).body(new APIResponse<Session>(false, "Incorrect email/password", null));
        }
        authRequest.setSuccess(true);
        authRepository.save(authRequest);
        
        Session session = new Session(user.getId());
        session = sessionRepository.save(session);
        return ResponseEntity.ok(new APIResponse<Session>(true, "Login Successful", session));
    }

    public boolean validateSession(Long sessionId){
        Optional<Session> Osession = sessionRepository.findById(sessionId);
        if (!Osession.isPresent()){
            return false;
        }

        Session session = Osession.get();
        if (session.getExpireDate().isBefore(LocalDateTime.now())){  //session expired
            return false;
        }

        return true;
    }

    public Session getSessionById(Long sessionId){
        Optional<Session> Osession = sessionRepository.findById(sessionId);
        if (!Osession.isPresent()){
            throw new IllegalArgumentException("Session does not exist!");
        }

        return Osession.get();
    }
}
