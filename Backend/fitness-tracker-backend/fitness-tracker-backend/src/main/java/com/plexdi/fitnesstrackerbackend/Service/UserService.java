package com.plexdi.fitnesstrackerbackend.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.plexdi.fitnesstrackerbackend.Repository.UserRepository;
import com.plexdi.fitnesstrackerbackend.model.UserEntity;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity login(String username, String email, String password){
        Optional<UserEntity> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()){
            UserEntity user = userOpt.get();

            if (passwordEncoder.matches(password, user.getPassword())){
                return user;
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    public UserEntity saveUser(String username, String password, String firstName, String lastName, String email, LocalDate dob) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists. Try logging in.");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("This email already exists. Try a new email.");
        }

        UserEntity user = new UserEntity();
        LocalDate date = LocalDate.now();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); 
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCreatedAt(date);
        user.setDob(dob);

        return userRepository.save(user);
        //for tomorrow make sure to add the features that return a response 
    }
}
