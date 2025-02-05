package com.coolserver.server.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    static String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz"; 

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
   
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public String getHashedPassword(String password, String salt) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String s = password + salt;
        byte[] hashedBytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashedBytes); 
    }

    public String generateSalt(){ //default length 16
        String salt = "";
        for (int i = 0; i < 16; i++){
            int index = (int) (Math.random() * AlphaNumericString.length());
            salt += AlphaNumericString.substring(index, index + 1);
        }

        return salt;
    }

    private String bytesToHex(byte[] bytes){
        String hex = "";
        for (int i = 0; i < bytes.length; i++){
            byte b = bytes[i];
            hex += String.format("%02X", b);
        }

        return hex;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
