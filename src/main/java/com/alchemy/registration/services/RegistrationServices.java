package com.alchemy.registration.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.alchemy.registration.datamodel.RegistrationModel;
import com.alchemy.registration.exceptions.UserExistException;
import com.alchemy.registration.exceptions.UserNotFoundException;
import com.alchemy.registration.repository.RegistrationRepository;
import com.alchemy.registration.validations.EmailValidations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.alchemy.registration.validations.Constants.*;


@Service
@Slf4j
public class RegistrationServices {


    @Autowired
    private final RegistrationRepository registrationRepository;

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;


    private EmailValidations emailValidations = new EmailValidations();

    RegistrationServices(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }


    public List<RegistrationModel> getAllDetails() {
        return registrationRepository.findAll();
    }

    public String UserRegistration(RegistrationModel user) throws UserExistException {
        /*
         *   Email Validation
         */
        boolean isValid = emailValidations.validateEmail(user.getEmail());
        if (Boolean.FALSE.equals(isValid)) {
            throw new RuntimeException(EmailIDNotValid);
        }

        CharSequence pass = user.getPassword();
        String p = new String(new BCryptPasswordEncoder().encode(pass));

        log.info("Password: " + p);
        user.setPassword(p);
        if (registrationRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserExistException(UserExists);
            //throw new UserExistsExceptions(LocalDate.now(),406,"User Already Exists");
        } else {
            registrationRepository.save(user);
        }

        rabbitMessagingTemplate.convertAndSend("myQueue1","User Registered");
        return UserRegistered;
    }

    public Optional<RegistrationModel> getUserDetails(Long id) throws UserNotFoundException {
        Optional<RegistrationModel> user = registrationRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException(UserNotFound);

        }
        return user;
    }
    @Transactional
    public String deleteUserRecord(Long id) {
        Optional<RegistrationModel> user = registrationRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException(UserNotFound);
        }
        registrationRepository.deleteById(id);

        return "User Deleted";
    }
}
