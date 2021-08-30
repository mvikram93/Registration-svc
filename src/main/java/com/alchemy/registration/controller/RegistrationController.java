package com.alchemy.registration.controller;

import com.alchemy.registration.config.DevYmlConfig;
import com.alchemy.registration.config.YmlConfig;
import com.alchemy.registration.datamodel.RegistrationModel;
import com.alchemy.registration.exceptions.UserNotFoundException;
import com.alchemy.registration.services.RegistrationServices;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class RegistrationController {
    @Autowired
    private final RegistrationServices registerServices;

    @Autowired
    private YmlConfig yml;
    @Autowired
    private DevYmlConfig devYmlConfig;

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private RegistrationController(RegistrationServices registerServices) {
        this.registerServices = registerServices;

    }


    @GetMapping(value = "/admin/register")
    private ResponseEntity<List<RegistrationModel>> getRegisteredUser() {
        return new ResponseEntity<>(registerServices.getAllDetails(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/register/{id}")
    private ResponseEntity<String> deleteUser(@NotNull @PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<String>(registerServices.deleteUserRecord(id), HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:2020")
    @PostMapping(value = "/user/register")
    private String registerUser(@NotNull @RequestBody RegistrationModel user) {
        log.info("User: " + user);
        log.info("User1 : " + yml.getValue() + "---" + devYmlConfig.getValue());
        return registerServices.UserRegistration(user);
    }

    @GetMapping(value = "/admin/register/{id}")
    @PreAuthorize("isAuthenticated()")
    private Optional<RegistrationModel> getRegisteredUserName(@PathVariable("id") Long id) throws UserNotFoundException {
        return registerServices.getUserDetails(id);
    }

}
