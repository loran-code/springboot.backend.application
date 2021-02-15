package nl.akker.springboot.backend.application.service.security;

import nl.akker.springboot.backend.application.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> listAll();

    Customer getById(long id);

    Customer saveOrUpdate(Customer customer);

    void delete(Long id);

    Customer saveOrUpdateCustomerForm(Customer customer);

}
