//package com.step.managementbankcard.controller;
//
//import com.step.managementbankcard.DTO.AuthRequest;
//import com.step.managementbankcard.DTO.AuthResponse;
//import com.step.managementbankcard.DTO.RegisterRequest;
//import com.step.managementbankcard.models.BankCard;
//import com.step.managementbankcard.repository.ManagementRepo;
//import com.step.managementbankcard.service.JwtService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Role;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.security.core.userdetails.User;
//
//
//import java.util.Collections;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private ManagementRepo managementRepo;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/register")
//    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
//        if (managementRepo.findByEmail(request.getEmail()).isPresent()) {
//            throw new RuntimeException("Email already in use");
//        }
//
//        BankCard bankCard = new BankCard();
//        bankCard.setEmail(request.getEmail());
//        bankCard.setPassword(passwordEncoder.encode(request.getPassword()));
//        bankCard.setRole();
//        managementRepo.save();
//
//        String jwt = jwtService.generateToken(bankCard);
//        return ResponseEntity.ok(new AuthResponse(jwt));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        BankCard bankCard = managementRepo.findByEmail(request.getEmail())
//                .orElseThrow();
//        String jwt = jwtService.generateToken(bankCard);
//        return ResponseEntity.ok(new AuthResponse(jwt));
//    }
//
//}
