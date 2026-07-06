package com.example.employeeapi.entity;

import com.example.employeeapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(String... args){
           if(userRepository.findByUsername("employee_admin").isEmpty())
           {
               User testUser = new User();
               testUser.setUsername("employee_admin");
               testUser.setPassword(passwordEncoder.encode("password123"));

               userRepository.save(testUser);
               System.out.println(">>> Database ready. Test Login - User: employee_admin | Password: password123");

           }

    }

}
