package nl.akker.springboot.backend.application.controller;

import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Collection<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) throws Exception {
        customerService.updateCustomer(id, customer);
        return ResponseEntity.noContent().build();
    }
}
