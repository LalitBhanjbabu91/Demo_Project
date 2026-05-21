package com.example.employeeapi.service;

import com.example.employeeapi.entity.Employee;
import com.example.employeeapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.employeeapi.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedDetails) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(updatedDetails.getName());
                    employee.setRole(updatedDetails.getRole());
                    return repository.save(employee);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
    }
}
