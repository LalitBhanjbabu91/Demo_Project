package com.example.employeeapi.controller; // <-- FIX THIS LINE

import com.example.employeeapi.entity.Employee; // <-- FIX THIS LINE
import com.example.employeeapi.service.EmployeeService; // <-- FIX THIS LINE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee saved = service.createEmployee(employee);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee details) {
        Employee updated = service.updateEmployee(id, details);
        return ResponseEntity.ok(updated);
    }
}