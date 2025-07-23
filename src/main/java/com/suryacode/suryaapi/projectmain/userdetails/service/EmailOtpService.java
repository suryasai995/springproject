package com.suryacode.suryaapi.projectmain.userdetails.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailOtpService {

    @Autowired
    private JavaMailSender mailSender;

    // Stores OTP temporarily. Use Redis or DB for production
    private final Map<String, String> otpCache = new ConcurrentHashMap<>();

    public String generateOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpCache.put(email, otp);
        return otp;
    }

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP for Email Verification");
        message.setText("Your One-Time Password (OTP) is: " + otp + "\nIt is valid for 5 minutes.");

        mailSender.send(message);
    }

    public boolean verifyOtp(String email, String inputOtp) {
        String savedOtp = otpCache.get(email);
        return savedOtp != null && savedOtp.equals(inputOtp);
    }

    public void clearOtp(String email) {
        otpCache.remove(email);
    }
}
