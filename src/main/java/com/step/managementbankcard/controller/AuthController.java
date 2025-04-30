package com.step.managementbankcard.controller;

import com.step.managementbankcard.DTO.AuthRequest;
import com.step.managementbankcard.DTO.AuthResponse;
import com.step.managementbankcard.DTO.RegisterRequest;
import com.step.managementbankcard.models.BankCard;
import com.step.managementbankcard.models.Role;
import com.step.managementbankcard.models.User;
import com.step.managementbankcard.repository.ManagementRepo;
import com.step.managementbankcard.service.JwtService;
import com.step.managementbankcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);
        user.isEnabled();

        User savedUser = userService.save(user);

        String jwt = jwtService.generateToken(savedUser);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));

    }

}
