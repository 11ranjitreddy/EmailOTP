package com.Email.OTP.Service;

public interface UserService {
    void sendOtp(String email);
    boolean verifyOtp(String email,String otp);
}
