package org.example.realestatemanagementsystem.logic;

import java.util.regex.Pattern;

public class InputChecker {
    public boolean checkingEmail(String email){
        Pattern pattern = Pattern.compile(
                "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        return pattern.matcher(email).matches();
    }
    public boolean checkingUserName(String username){
        Pattern pattern = Pattern.compile(
                "^[\\p{L}\\p{M}' .\\-]+$");
        return pattern.matcher(username).matches();
    }
    public boolean checkingPassWord(String password){
        Pattern pattern = Pattern.compile(
                "^[\\p{L}\\p{N}\\p{P}\\p{S}]{8,32}$\n");
           //     "^[a-zA-Z0-9]{8,32}$");
        return pattern.matcher(password).matches();
    }
}
