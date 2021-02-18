package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.*;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import nl.akker.springboot.backend.application.repository.EmployeeRepository;
import nl.akker.springboot.backend.application.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.EntityManagerBeanDefinitionRegistrarPostProcessor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
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

    public void test(){
        Employee empl = GetEmployee();

    }

    public Employee GetEmployee(){
        Employee guyon = new Employee();
        employeeRepository.save(guyon);
        return guyon;
    }
}

