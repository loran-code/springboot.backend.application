package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;

import java.util.Collection;
import java.util.Map;

public interface CustomerService {

    Collection<Customer> getCustomers();
    Customer getCustomerById(Long id);
    ReturnObject createCustomer(Customer customer);
    ReturnObject updateCustomer(Long id, Customer customer);
    long partialUpdateCustomer(Long id, Map<String, String> fields);
    void deleteCustomer(Long id);
    Collection<Customer> getCustomersByLastname(String lastName);
}
