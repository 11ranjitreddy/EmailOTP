package com.Email.OTP.Entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name ="userotp" )

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String email;
    private String otp;
    private Instant createdAt;


    public User(){}

    public User(String email ,String otp,Instant createdAt){
        this.email=email;
        this.otp=otp;
        this.createdAt=createdAt;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    public String getOtp(){return otp;}
    public void setOtp(String otp){this.otp=otp;}

    public Instant getCreatedAt(){return createdAt;}
    public void  setCreatedAt(Instant createdAt){this.createdAt=createdAt;}
}
