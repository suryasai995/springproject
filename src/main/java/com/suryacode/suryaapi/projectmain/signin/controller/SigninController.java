package com.suryacode.suryaapi.projectmain.signin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suryacode.suryaapi.projectmain.signin.dto.signindto;
import com.suryacode.suryaapi.projectmain.signin.service.signinservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/signup")
public class SigninController {

    private final signinservice signinservice;

 public SigninController(signinservice signinservice) {
        this.signinservice = signinservice;
    }

    @GetMapping 
    public ResponseEntity<Map<String, Object>> getMethodName(        
   @RequestParam(required = false) Map<String, String> allParams
    // @RequestParam(required = false) String email,
    ) {
        
        String email = allParams.get("email");
        String password = allParams.get("password");

        HashMap<String, Object> response = new HashMap<>();

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
        response.put("status", 400);
        response.put("message", "Missing or empty parameters: email or password");
        return ResponseEntity.badRequest().body(response);
    }
    try {
       signindto accountNew = new signindto(allParams.get("email"), allParams.get("password"));
        signindto user = signinservice.createAccount(accountNew);
         String uuid = UUID.randomUUID().toString();
        System.out.println("Generated UUID: " + uuid);
        response.put("status", 200);
        response.put("message", "Success");
        response.put("email", user.getEmail());
        response.put("id", user.getId());
        response.put("session-id", uuid);
        response.put("status", 201);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    } catch (Exception e) {
         Map<String, Object> error = new HashMap<>();
        error.put("status", 400);
        error.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
       
    }

    

     @PostMapping
    public ResponseEntity<Map<String, Object>> postMethodName(@RequestBody signindto allbody) {
       Map<String, Object> response = new HashMap<>();
        if (allbody.getEmail() == null || allbody.getPassword() == null ||
            allbody.getEmail().isEmpty() || allbody.getPassword().isEmpty()) {
            response.put("status", 400);
            response.put("message", "Missing email or password");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            signindto user = signinservice.getAccount(allbody);
            String uuid = UUID.randomUUID().toString();

            response.put("status", 200);
            response.put("message", "Success");
            response.put("userData", user);
            response.put("session-id", uuid);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace(); // Log exception for debugging
            Map<String, Object> error = new HashMap<>();
            error.put("status", 500);
            error.put("message",   e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }    
    


    
}
