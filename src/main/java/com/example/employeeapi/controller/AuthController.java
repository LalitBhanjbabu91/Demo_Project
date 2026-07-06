package com.example.employeeapi.controller;

import com.example.employeeapi.security.JwtService;
import com.example.employeeapi.security.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request)
//    {
//        System.out.println("Received Username: " + request.getUsername());
//        System.out.println("Received Password: " + request.getPassword());
//
//        if("admin".equals(request.getUsername()) && "password".equals(request.getPassword())){
//            String token = jwtService.generateToken(request.getUsername());
//
//            return ResponseEntity.ok(Map.of("Token", token));
//
//        }
//        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials supplied"));
//    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request)
    {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            String token = jwtService.generateToken(authentication.getName());
            return ResponseEntity.ok(Map.of("Token", token));

        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Database validation failed. Invalid credentials."));
        }
    }
}
