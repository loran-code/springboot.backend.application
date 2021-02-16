package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.model.Employee;
import nl.akker.springboot.backend.application.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

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
