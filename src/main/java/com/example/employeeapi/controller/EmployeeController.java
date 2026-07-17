package com.example.employeeapi.controller; // <-- FIX THIS LINE

import com.example.employeeapi.entity.Employee; // <-- FIX THIS LINE
import com.example.employeeapi.repository.EmployeeRepository;
import com.example.employeeapi.service.EmployeeService; // <-- FIX THIS LINE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(@PageableDefault(size = 10, sort = "id", direction =Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.getAllEmployees(pageable));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully by id: "+id);
    }
}