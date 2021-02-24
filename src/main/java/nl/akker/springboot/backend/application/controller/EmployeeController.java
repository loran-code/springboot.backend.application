package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.dbmodels.Employee;
import nl.akker.springboot.backend.application.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/employee/")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(path = "all")
    public Collection<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public ResponseEntity<Object> registerNewEmployee(@RequestBody @Valid Employee employee) {
        employeeService.createEmployee(employee);
        return ResponseEntity.ok().body("New Employee has been created: " + employee);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") Long id, @RequestBody @Valid Employee employee) {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok().body("The employee details have been updated: " + employee);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> partiallyUpdateEmployee(@PathVariable("id") Long id, @RequestBody @Valid Map<String, String> fields) {
        employeeService.partialUpdateEmployee(id, fields);
        return ResponseEntity.ok().body("The specified employee details have been updated: " + fields);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().body("The employee with id " + id + " has been deleted");
    }
}
