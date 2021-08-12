package com.alchemy.registration.controller;

import com.alchemy.registration.datamodel.RegistrationModel;
import com.alchemy.registration.exceptions.UserNotFoundException;
import com.alchemy.registration.services.RegistrationServices;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class RegistrationController {
    @Autowired
    private final RegistrationServices registerServices;

    private RegistrationController(RegistrationServices registerServices) {
        this.registerServices = registerServices;
    }


    @GetMapping(value = "/admin/register")
    private ResponseEntity<List<RegistrationModel>> getRegisteredUser() {
        return new ResponseEntity<>(registerServices.getAllDetails(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/register/{id}")
    private ResponseEntity<String> deleteUser(@NotNull @PathVariable("id") Long id ) throws UserNotFoundException{
        return new ResponseEntity<String>(registerServices.deleteUserRecord(id), HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:2020")
    @PostMapping(value = "/user/register")
    private String registerUser(@NotNull @RequestBody RegistrationModel user) {
        log.info("User: "+user);
        return registerServices.UserRegistration(user);
    }

    @GetMapping(value = "/admin/register/{id}")
    private Optional<RegistrationModel> getRegisteredUserName(@PathVariable("id") Long id) throws UserNotFoundException {
      return registerServices.getUserDetails(id);
    }

}
