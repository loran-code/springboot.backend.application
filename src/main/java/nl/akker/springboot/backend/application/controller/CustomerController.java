package nl.akker.springboot.backend.application.controller;

import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api/customer/")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "all")
    public Collection<Customer> getCustomers() {
        System.out.println("GET REQUEST...");
        return customerService.getCustomers();
    }

    @GetMapping(path = "{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public Collection<Customer> getCustomersByLastName(@RequestParam(name="lastname") String lastName){
        return customerService.getCustomersByLastName(lastName);
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        System.out.println("POST REQUEST...");
        System.out.println(customer);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        customerService.updateCustomer(id, customer);
        System.out.println("PUT REQUEST...");
        System.out.println(customer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        System.out.println("DELETE REQUEST FOR CUSTOMER WITH ID " + id);
        customerService.deleteCustomer(id);
    }
}
