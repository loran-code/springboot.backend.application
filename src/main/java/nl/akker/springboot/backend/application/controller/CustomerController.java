package nl.akker.springboot.backend.application.controller;

import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.service.security.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }
}
