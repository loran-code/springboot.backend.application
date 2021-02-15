package nl.akker.springboot.backend.application.service;

//import nl.akker.springboot.backend.application.exceptions.RecordNotFoundException;

import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public long createCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer.getId();
    }

    @Override
    public void updateCustomer(long id, Customer customer) throws Exception {
        if (!customerRepository.existsById(id)) throw new Exception();
        Customer updateCustomer = customerRepository.findById(id).orElse(null);
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        customerRepository.save(updateCustomer);
    }

    @Override
    public void deleteCustomer(long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "Customer with id " + id + " does not exists");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Collection<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Collection<Customer> getCustomersByLastName(String name) {
        return customerRepository.findAllByLastName(name);
    }

    @Override
    public Optional<Customer> getCustomerById(long id) throws Exception {
        if (!customerRepository.existsById(id)) throw new Exception();
        return customerRepository.findById(id);
    }

    @Override
    public boolean customerExistsById(long id) {
        return customerRepository.existsById(id);
    }
}