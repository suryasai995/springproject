package com.suryacode.suryaapi.projectmain.signin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suryacode.suryaapi.projectmain.sessionid.dto.SessionidDto;
import com.suryacode.suryaapi.projectmain.sessionid.service.serviceSession;
import com.suryacode.suryaapi.projectmain.signin.dto.signindto;
import com.suryacode.suryaapi.projectmain.signin.service.signinservice;
import com.suryacode.suryaapi.projectmain.userdetails.dto.UserDto;
import com.suryacode.suryaapi.projectmain.userdetails.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/signup")
public class SigninController {

    private final signinservice signinservice;
    public final serviceSession serviceSession;

    @Autowired
    private UserService userService;

    public SigninController(signinservice signinservice, serviceSession serviceSession) {
        this.signinservice = signinservice;
        this.serviceSession = serviceSession;
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
    public ResponseEntity<Map<String, Object>> postMethodName(
            @RequestBody Map<String, Object> allbodys,
            @RequestHeader Map<String, Object> headers) {
        signindto allbody = new signindto((String) allbodys.get("email"), (String) allbodys.get("password"));
        Map<String, Object> response = new HashMap<>();
        if (allbody.getEmail() == null || allbody.getPassword() == null ||
                allbody.getEmail().isEmpty() || allbody.getPassword().isEmpty()) {
            response.put("status", 400);
            response.put("message", "Missing email or password");
            return ResponseEntity.badRequest().body(response);
        }
        // System.out.println(headers.get("box-id"));

        try {
            signindto users;
            Map<String, Object> userdetails = new HashMap<>();
            String status = (String) allbodys.get("status");
            String uuid = UUID.randomUUID().toString();

            if (status.equals("0")) {
                users = signinservice.createAccount(allbody);
                userdetails.put("email", users.getEmail());
                userdetails.put("id", users.getId());
                userdetails.put("details", false);
            } else {

                users = signinservice.getAccount(allbody);
                UserDto UserDto = null;
                try {
                    UserDto = userService.getUserById(users.getId());
                    userdetails.put("details", true);
                    SessionidDto sData = new SessionidDto(uuid, (String) headers.get("box-id"), users.getId());
                    serviceSession.createBySessionId(sData);

                    response.put("session-id", uuid);

                } catch (Exception e) {

                    userdetails.put("email", users.getEmail());
                    userdetails.put("id", users.getId());
                    userdetails.put("details", false);
                }

                userdetails.put("userData", UserDto);
            }

            response.put("status", 200);
            response.put("message", "Success");
            response.put("userData", userdetails);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace(); // Log exception for debugging
            Map<String, Object> error = new HashMap<>();
            error.put("status", 500);
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

}
