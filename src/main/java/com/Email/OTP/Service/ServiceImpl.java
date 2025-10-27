package com.Email.OTP.Service;

import com.Email.OTP.Entity.User;
import com.Email.OTP.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.Random;

@Service
public class ServiceImpl implements UserService{

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendOtp(String email) {

        String otp=String.format("%06d",new Random().nextInt(1000000));
        User user=new User(email,otp, Instant.now());
        userRepository.save(user);

        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP verification");
        message.setText("Your OTP for verification  " +otp+ "  valid for 2 minutes only");
        mailSender.send(message);

    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        Optional<User> latestotp=userRepository.findTopByEmailOrderByCreatedAtDesc(email);
        if(latestotp.isPresent()){
            User entity=latestotp.get();
            boolean notExpired= Duration.between(entity.getCreatedAt(),Instant.now()).toMinutes()<2;
            return notExpired && entity.getOtp().equals(otp);
        }

        return false;
    }
}
