package nl.akker.springboot.backend.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    @GetMapping
    public String getCustomers() {
        return "hello world";
    }
}
