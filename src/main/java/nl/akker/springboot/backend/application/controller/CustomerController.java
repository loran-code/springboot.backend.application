package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/customer/")
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
    public Collection<Customer> getCustomersByLastname(@RequestParam(name = "lastname") String lastname) {
        return customerService.getCustomersByLastname(lastname);
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
        return ResponseEntity.ok().body("The specified customer details have been updated: " + fields);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body("The customer with id " + id + " has been deleted");
    }
}
