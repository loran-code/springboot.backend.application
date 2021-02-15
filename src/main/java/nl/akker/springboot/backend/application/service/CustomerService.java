package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.Customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerService {

    long createCustomer(Customer customer);

    void updateCustomer(long id, Customer customer) throws Exception;

    void deleteCustomer(long id);

    Collection<Customer> getCustomers();

    Collection<Customer> getCustomersByLastName(String name);

    Optional<Customer> getCustomerById(long id) throws Exception;

    boolean customerExistsById(long id);


}
