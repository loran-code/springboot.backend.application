package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.model.Employee;
import nl.akker.springboot.backend.application.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public Collection<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping
    public void registerNewEmployee(@RequestBody Customer customer) {
        employeeService.createEmployee(customer);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) throws Exception {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.noContent().build();
    }
}
