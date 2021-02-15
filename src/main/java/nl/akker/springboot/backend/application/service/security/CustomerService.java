package nl.akker.springboot.backend.application.service.security;

import nl.akker.springboot.backend.application.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface CustomerService {

    long createCustomer(Customer customer);

    void updateCustomer(long id, Customer customer);

    void deleteCustomer(long id);

    List<Customer> getCustomers();

    List<Customer> getCustomersByLastName(String name);

    Optional<Customer> getCustomerById(long id);

    boolean customerExistsById(long id);
}
