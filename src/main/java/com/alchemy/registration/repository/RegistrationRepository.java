package com.alchemy.registration.repository;

import com.alchemy.registration.datamodel.RegistrationModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationModel,Long> {

    Optional<RegistrationModel> findByEmail(String email);



}
