package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.*;
import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Collection<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer with id " + id + " not found"));
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
            throw new NotFoundException("Customer with " + id + " has not been found thus can not be updated");
        }
        // TODO add exception errors when the firstname, lastname, phone, or email have not been specified.
        Customer updateCustomer = customerRepository.findById(id).orElse(null);
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        updateCustomer.setPhone(customer.getPhone());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setCity(customer.getCity());
        updateCustomer.setZip(customer.getZip());
        updateCustomer.setModified(java.time.LocalDateTime.now());
        customerRepository.save(updateCustomer);
        //        TODO add message in body. PUT request has been executed successfully.
    }

    @Override
    public void partialUpdateCustomer(Long id, Map<String, String> fields) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("Wrong details have been given", HttpStatus.BAD_REQUEST);
        }
        Customer updateCustomer = customerRepository.findById(id).orElse(null);
        for (String field : fields.keySet()) {
            switch (field) {
                case "firstName" -> updateCustomer.setFirstName(fields.get(field));
                case "lastName" -> updateCustomer.setLastName(fields.get(field));
                case "phone" -> updateCustomer.setPhone(fields.get(field));
            }
        }
        customerRepository.save(updateCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("The specified id " + id + " has not been found", HttpStatus.BAD_REQUEST);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Collection<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findAllByLastName(lastName);
    }
}
