package com.Email.OTP.Controller;

import com.Email.OTP.Service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private ServiceImpl serviceImpl;

    @PostMapping("/send-otp")

    public ResponseEntity<?> sendOtp(@RequestBody Map<String,String> request){
        String email = request.get("email");
        if(email == null || email.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("message","Email is required"));

        }
        serviceImpl.sendOtp(email);
        return ResponseEntity.ok(Map.of("message","OTP send Successfully"));
    }
    @PostMapping("/verify-otp")

    public ResponseEntity<?> verifyOtp(@RequestBody Map<String,String> request){
     String email=request.get("email");
     String otp=request.get("otp");

     if(email== null || otp==null){
         return ResponseEntity.badRequest().body(Map.of("message","Email and OTP are required"));

     }
     boolean isValid= serviceImpl.verifyOtp(email,otp);
     if(isValid){
         return ResponseEntity.ok(Map.of("message","OTP verified Successfully"));
     }else{
         return ResponseEntity.status(400).body(Map.of("message","Invalid or expired OTP"));
     }
    }

}
