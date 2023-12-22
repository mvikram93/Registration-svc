package com.alchemy.registration.datamodel;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity(name = "tbl_registration")
public class RegistrationModel {

    public RegistrationModel(String name,
                             String email,
                             String dob,
                             Long mobile_no,
                             Boolean disclosure_acceptance,
                             Date registered_date_time) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.mobile_no = mobile_no;
        this.disclosure_acceptance = disclosure_acceptance;
        this.registered_date_time = registered_date_time;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "sequence_generator",
            sequenceName = "sequence_generator",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"

    )
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String 	email;
    @Column(name = "password")
    private String password;
    @Column(name = "dob")
    private String dob;
    @Column(name = "mobile_no")
    private Long mobile_no;
    @Column(name = "disclosure_acceptance")
    private Boolean disclosure_acceptance;
    @Column(name = "registered_date_time")
    private  Date registered_date_time;

    public RegistrationModel() {
    }


    public Long getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Long getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(Long mobile_no) {
        this.mobile_no = mobile_no;
    }

    public Boolean getDisclosure_acceptance() {
        return disclosure_acceptance;
    }

    public void setDisclosure_acceptance(Boolean disclosure_acceptance) {
        this.disclosure_acceptance = disclosure_acceptance;
    }

    public Date getRegistered_date_time() {
        return new Date();
    }

    public void setRegistered_date_time(Date registered_date_time) {
        this.registered_date_time = registered_date_time;
    }

    @Override
    public String toString() {
        return "RegistrationModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", mobile_no=" + mobile_no +
                ", disclosure_acceptance=" + disclosure_acceptance +
                ", registered_date_time=" + registered_date_time +
                '}';
    }
}
