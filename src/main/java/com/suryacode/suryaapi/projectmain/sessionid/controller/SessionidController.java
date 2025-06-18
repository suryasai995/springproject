package com.suryacode.suryaapi.projectmain.sessionid.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suryacode.suryaapi.projectmain.sessionid.dto.SessionidDto;
import com.suryacode.suryaapi.projectmain.sessionid.service.serviceSession;
import com.suryacode.suryaapi.projectmain.userdetails.dto.UserDto;
import com.suryacode.suryaapi.projectmain.userdetails.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/user-update")
public class SessionidController {

    @Autowired
    private serviceSession serviceSession;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> postMethodName(@RequestBody UserDto allEntity,
            @RequestHeader Map<String, Object> headers) {

        try {

            String uuid = UUID.randomUUID().toString();
            UserDto createdUser = userService.createUser(allEntity);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 201);
            response.put("user", createdUser);
            // UserDto UserDto = userService.getUserById(createdUser.getUserid());
            SessionidDto sData = new SessionidDto(uuid, (String) headers.get("box-id"), createdUser.getUserid());
            serviceSession.createBySessionId(sData);

            response.put("session-id", uuid);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", 400);
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

}
