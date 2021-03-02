package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmployeeService {

    Collection<Employee> getEmployees();

    Employee getEmployeeById(Long id);

    ReturnObject createEmployee(Employee employee);

    ReturnObject updateEmployee(Long id, Employee employee);

    ReturnObject partialUpdateEmployee(Long id, Map<String, String> fields);

    void deleteEmployee(Long id);
}
