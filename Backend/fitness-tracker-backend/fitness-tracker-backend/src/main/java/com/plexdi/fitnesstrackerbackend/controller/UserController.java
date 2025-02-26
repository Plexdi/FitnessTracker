package com.plexdi.fitnesstrackerbackend.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plexdi.fitnesstrackerbackend.Service.UserService;
import com.plexdi.fitnesstrackerbackend.model.UserEntity;

import io.micrometer.core.ipc.http.HttpSender.Response;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController { // Fixed class name typo

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserEntity user){
        try {
            UserEntity loginUser = userService.login(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
            );
    
            // Return only safe user details
            return ResponseEntity.ok().body(Map.of(
                "message", "User logged in successfully",
                "username", loginUser.getUsername(),
                "email", loginUser.getEmail(),
                "id", loginUser.getId()
            ));
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }
    

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        try {
            UserEntity savedUser = userService.saveUser(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDob()
            );
    
            // Return JSON instead of plain text
            return ResponseEntity.ok().body(Map.of("message", "User registered successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }
    
}