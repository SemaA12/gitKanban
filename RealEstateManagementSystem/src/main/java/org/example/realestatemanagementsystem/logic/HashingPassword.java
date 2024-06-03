package org.example.realestatemanagementsystem.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingPassword {
    public String doHashing(String password){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] byteArray = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for(byte b : byteArray){
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        }catch(NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
        return "";
    }
}
