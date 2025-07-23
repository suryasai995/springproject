package com.suryacode.suryaapi.projectmain.userdetails.controller;

import com.suryacode.suryaapi.projectmain.userdetails.model.User;
import com.suryacode.suryaapi.projectmain.userdetails.repository.UserRepository;
import com.suryacode.suryaapi.projectmain.userdetails.service.EmailOtpService;
import com.suryacode.suryaapi.projectmain.userdetails.service.UserService;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private EmailOtpService otpService;

    @Autowired
    private UserService userService; // your existing service
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/send")
    public ResponseEntity<?> sendOtp(@RequestParam String email) {
         Optional<User> existingUser = userRepository.findByEmail(email);

    if (existingUser.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("status", 404, "message", "Email ID not registered: " + email));
    }

    String otp = otpService.generateOtp(email);
    otpService.sendOtpEmail(email, otp);

    return ResponseEntity.ok(Map.of("status", 200, "message", "OTP sent to " + email));
}
     
    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        if (otpService.verifyOtp(email, otp)) {
            userService.verifyEmail(email); // sets isEmailVerified = true
            otpService.clearOtp(email);
            return ResponseEntity.ok(Map.of("message", "Email verified successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid OTP"));
        }
    }
}
