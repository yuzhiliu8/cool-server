package com.coolserver.server.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    static String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz"; 

    public static String getHashedPassword(String password, String salt) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String s = password + salt;
            byte[] hashedBytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedBytes); 

        } catch (NoSuchAlgorithmException e){
            System.out.println("No such algorithm");
            return "";
        }
    }

    public static String generateSalt(){ //default length 16
        String salt = "";
        for (int i = 0; i < 16; i++){
            int index = (int) (Math.random() * AlphaNumericString.length());
            salt += AlphaNumericString.substring(index, index + 1);
        }

        return salt;
    }

    public static String bytesToHex(byte[] bytes){
        String hex = "";
        for (int i = 0; i < bytes.length; i++){
            byte b = bytes[i];
            hex += String.format("%02X", b);
        }

        return hex;
    }
}
