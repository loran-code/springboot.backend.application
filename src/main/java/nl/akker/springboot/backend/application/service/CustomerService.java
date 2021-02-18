package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.Customer;

import java.util.Collection;
import java.util.Map;

public interface CustomerService {

    Collection<Customer> getCustomers();
    Customer getCustomerById(Long id);
    long createCustomer(Customer customer);
    void updateCustomer(Long id, Customer customer);
    void partialUpdateCustomer(Long id, Map<String, String> fields);
    void deleteCustomer(Long id);
    Collection<Customer> getCustomersByLastName(String lastName);
}
