package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.model.Employee;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeService extends UserDetails {

    long createEmployee(Customer customer);

    void updateEmployee(long id, Employee employee) throws Exception;

    void deleteEmployee(long id);

    Collection<Employee> getEmployees();

    Collection<Employee> getEmployeesByLastName(String name);

    Optional<Employee> getEmployeeById(long id) throws Exception;

    boolean employeeExistsById(long id);
}
