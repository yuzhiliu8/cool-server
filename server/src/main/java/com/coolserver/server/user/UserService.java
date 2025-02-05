package com.coolserver.server.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

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

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new IllegalArgumentException("No User found with id: " + id);
        }

        return user.get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new IllegalArgumentException("No User found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser){
        if (!userRepository.existsById(id)){
            throw new IllegalArgumentException("No User found with id: " + id);
        }
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
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
}
