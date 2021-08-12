package com.alchemy.registration.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidations {
    private static final String regex = "^(.+)@(.+)$";

    public Boolean validateEmail(String email){
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(email);
        Boolean isValid = match.matches();
        System.out.println("Valid : "+isValid);
        return isValid;
    }
}
