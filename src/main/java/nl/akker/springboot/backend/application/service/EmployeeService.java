package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.tables.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmployeeService {

    Collection<Employee> getEmployees();
    Employee getEmployeeById(Long id);
    long createEmployee(Employee employee);
    void updateEmployee(Long id, Employee employee);
    void partialUpdateEmployee(Long id, Map<String, String> fields);
    void deleteEmployee(Long id);
}
