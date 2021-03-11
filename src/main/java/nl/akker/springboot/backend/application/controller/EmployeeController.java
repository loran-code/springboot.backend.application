package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Employee;
import nl.akker.springboot.backend.application.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE', 'ROLE_BACKOFFICE')")
    public Collection<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE', 'ROLE_BACKOFFICE')")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> registerNewEmployee(@Valid @RequestBody Employee employee) {
        ReturnObject returnObject = employeeService.createEmployee(employee);
        return ResponseEntity.ok().body(returnObject);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> updateEmployee(@Valid @PathVariable("id") Long id, @RequestBody Employee employee) {
        ReturnObject returnObject = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok().body(returnObject);
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> partiallyUpdateEmployee(@Valid @PathVariable("id") Long id, @RequestBody Map<String, String> fields) {
        ReturnObject returnObject = employeeService.partialUpdateEmployee(id, fields);
        return ResponseEntity.ok().body(returnObject);
    }

    @GetMapping(value = "call")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> callCustomers() {
        ReturnObject returnObject = employeeService.callCustomers();
        return ResponseEntity.ok().body(returnObject);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().body("The employee with id " + id + " has been deleted");
    }
}
