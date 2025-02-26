package com.plexdi.fitnesstrackerbackend.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plexdi.fitnesstrackerbackend.Repository.UserRepository;
import com.plexdi.fitnesstrackerbackend.Service.JwtService;
import com.plexdi.fitnesstrackerbackend.Service.UserService;
import com.plexdi.fitnesstrackerbackend.model.UserEntity;

import io.micrometer.core.ipc.http.HttpSender.Response;

@CrossOrigin(origins = "fitness-tracker-flax.vercel.app", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController { 

    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public UserController(UserService userService, JwtService jwtService, UserRepository userRepository) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserEntity user) {
        try {
            Optional<UserEntity> foundUser = userRepository.findByUsername(user.getUsername());
            if (foundUser.isEmpty() || !userService.verifyPassword(user.getPassword(), foundUser.get().getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
            }

            // Generate JWT token
            String token = jwtService.generateToken(foundUser.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token, "username", foundUser.get().getUsername()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        try {
            token = token.replace("Bearer ", "");
            String username = jwtService.extractUsername(token);

            Optional<UserEntity> user = userRepository.findByUsername(username);
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
            }

            return ResponseEntity.ok(user.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid token"));
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