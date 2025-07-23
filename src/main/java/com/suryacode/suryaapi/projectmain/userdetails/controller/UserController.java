package com.suryacode.suryaapi.projectmain.userdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.suryacode.suryaapi.projectmain.userdetails.dto.UserDto;
import com.suryacode.suryaapi.projectmain.userdetails.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/info-user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
       List<UserDto> users = userService.getAllUsers();
       Map<String, Object> response = new HashMap<>();
       response.put("users", users);
       response.put("status", 200);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        try {
            UserDto user = userService.getUserById(id);
            Map<String , Object> response = new HashMap<>();
            response.put("user", user);
            response.put("status", 200);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
             Map<String , Object> response = new HashMap<>();
            response.put("status", 404);
            response.put("message", ex.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping
public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserDto userDto) {
    try {
        UserDto createdUser = userService.createUser(userDto);
        Map<String, Object> response = new HashMap<>();
        response.put("status", 201);
        response.put("user", createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 400);
        error.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}



}
