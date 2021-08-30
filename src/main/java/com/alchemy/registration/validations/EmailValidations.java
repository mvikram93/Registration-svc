package com.alchemy.registration.validations;

import com.alchemy.registration.controller.RegistrationController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidations {
    private static final String regex = "^(.+)@(.+)$";
    private static Logger log = LoggerFactory.getLogger(RegistrationController.class);
    public Boolean validateEmail(String email){
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(email);
        Boolean isValid = match.matches();
        log.info("Valid : "+isValid);
        return isValid;
    }
}
