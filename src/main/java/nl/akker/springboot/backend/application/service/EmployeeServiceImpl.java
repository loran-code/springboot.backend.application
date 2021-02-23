package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.tables.Employee;
import nl.akker.springboot.backend.application.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE', 'ROLE_BACKOFFICE')")
    public Collection<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE', 'ROLE_BACKOFFICE')")
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee with id " + id + " not found"));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public long createEmployee(Employee employee) {
        Employee createEmployee = employeeRepository.save(employee);
        createEmployee.setPassword(encoder.encode(createEmployee.getPassword()));
        createEmployee.setCreated(java.time.LocalDateTime.now());
        createEmployee.setModified(java.time.LocalDateTime.now());
        employeeRepository.save(createEmployee);
        return createEmployee.getId();
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public void updateEmployee(Long id, Employee employee) {
        if (!employeeRepository.existsById(id)) {
            throw new ApiRequestException("Employee with " + id + " has not been found thus can not be updated");
        }
        Employee updateEmployee = employeeRepository.findById(id).orElse(null);
        updateEmployee.setEmail(employee.getEmail());
        updateEmployee.setUsername(employee.getUsername());
        updateEmployee.setPassword(encoder.encode(updateEmployee.getPassword()));
//        updateEmployee.setRoles();
        updateEmployee.setModified(java.time.LocalDateTime.now());
        employeeRepository.save(updateEmployee);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void partialUpdateEmployee(Long id, Map<String, String> fields) {
        if (!employeeRepository.existsById(id)) {
            throw new ApiRequestException("Customer with id " + id + " has not been found thus can not be updated");
        }
        Employee updateEmployee = employeeRepository.findById(id).orElse(null);
        for (String field : fields.keySet()) {
            switch (field) {
                case "email" -> updateEmployee.setEmail(fields.get(field));
                case "username" -> updateEmployee.setUsername(fields.get(field));
                case "password" -> updateEmployee.setPassword(encoder.encode(fields.get(field)));
//                case "roles" -> updateEmployee.setErole(fields.get(field));
//                Todo update roles by api request
            }
        }
        updateEmployee.setModified(java.time.LocalDateTime.now());
        employeeRepository.save(updateEmployee);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteEmployee(Long id) {
            if (!employeeRepository.existsById(id)) {
                throw new ApiRequestException("Employee with id " + id + " has not been found");
            }
            employeeRepository.deleteById(id);
    }
}

