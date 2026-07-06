package com.example.employeeapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/protected")
public class ProtectedController {

    @GetMapping("/data")
    public String securedata(Principal principal){

        return "Access Granted! Welcome, " + principal.getName() +
                ". This resource is protected via the Spring Security Filter Chain using JWT.";
    }
}
