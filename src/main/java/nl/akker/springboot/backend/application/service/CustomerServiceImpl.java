package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.exceptions.RecordNotFoundException;
import nl.akker.springboot.backend.application.exceptions.UserNotFoundException;
import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Collection<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
//        if (!customerRepository.existsById(id)) { throw new RecordNotFoundException(); }
        return getCustomers()
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("customer not found"));
//                customerRepository.findById(id).orElse(null);
    }

    @Override
    public long createCustomer(Customer customer) {
        Customer createCustomer = customerRepository.save(customer);
        createCustomer.setCreated(java.time.LocalDateTime.now());
        createCustomer.setModified(java.time.LocalDateTime.now());
        customerRepository.save(createCustomer);
        return createCustomer.getId();
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
//        TODO add message in body. PUT request has been executed successfully.
//        TODO less code to be used to add all the parameters
        Customer updateCustomer = customerRepository.findById(id).orElse(null);
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        updateCustomer.setPhone(customer.getPhone());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setCity(customer.getCity());
        updateCustomer.setZip(customer.getZip());
        updateCustomer.setModified(java.time.LocalDateTime.now());
        customerRepository.save(updateCustomer);
    }

    @Override
    public void partialUpdateCustomer(Long id, Map<String, String> fields) {
        if (!customerRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        Customer updateCustomer = customerRepository.findById(id).orElse(null);
        for (String field : fields.keySet()) {
            switch (field) {
                case "firstName":
                    updateCustomer.setFirstName(fields.get(field));
                    break;
                case "lastName":
                    updateCustomer.setLastName(fields.get(field));
                    break;
                case "phone":
                    updateCustomer.setPhone(fields.get(field));
                    break;
            }
        }
        customerRepository.save(updateCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Collection<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findAllByLastName(lastName);
    }
}
