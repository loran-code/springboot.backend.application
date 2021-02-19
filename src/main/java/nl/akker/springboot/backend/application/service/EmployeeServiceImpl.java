package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.*;
import nl.akker.springboot.backend.application.repository.EmployeeRepository;
import nl.akker.springboot.backend.application.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    @Override
    public long createEmployee(Customer customer) {
        return 0;
    }

    @Override
    public void updateEmployee(long id, Employee employee) throws Exception {

    }

    @Override
    public void deleteEmployee(long id) {

    }

    @Override
    public Collection<Employee> getEmployees() {
        return null;
    }

    @Override
    public Collection<Employee> getEmployeesByLastName(String name) {
        return null;
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) throws Exception {
        return Optional.empty();
    }

    @Override
    public boolean employeeExistsById(long id) {
        return false;
    }

}

