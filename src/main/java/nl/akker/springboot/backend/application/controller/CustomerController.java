package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.BadRequestException;
import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/customers/")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "all")
    public Collection<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public Collection<Customer> getCustomersByLastName(@RequestParam(name = "lastname") String lastName) {
        return customerService.getCustomersByLastName(lastName);
    }

    @PostMapping
    public ResponseEntity<Object> registerNewCustomer(@RequestBody @Valid Customer customer) {
        customerService.createCustomer(customer);
        return ResponseEntity.ok().body("New customer has been created: " + customer);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long id, @RequestBody @Valid Customer customer) {
        customerService.updateCustomer(id, customer);
        return ResponseEntity.ok().body("The customer details have been updated: " + customer);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> partiallyUpdateCustomer(@PathVariable("id") Long id, @RequestBody @Valid Map<String, String> fields) {
        customerService.partialUpdateCustomer(id, fields);
        return ResponseEntity.ok().body("The specified customer details have been updated " + fields);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body("The customer with id " + id + " has been deleted");
    }

//    TODO set this a global variable project wide?
//    https://www.baeldung.com/exception-handling-for-rest-with-spring
    //  code snippet taken from https://dimitr.im/validating-the-input-of-your-rest-api-with-spring
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
