package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MECHANIC') or hasRole('FRONTOFFICE')  ")
    public Collection<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MECHANIC') or hasRole('FRONTOFFICE')  ")
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer with id " + id + " not found"));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MECHANIC') or hasRole('FRONTOFFICE')  ")
    public Collection<Customer> getCustomersByLastName(String lastName) {
        if (!customerRepository.existsByLastName(lastName)) {
            throw new NotFoundException("The specified last name " + lastName + " has not been found");
        }
        return customerRepository.findAllByLastName(lastName);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('FRONTOFFICE')")
    public long createCustomer(Customer customer) {
        Customer createCustomer = customerRepository.save(customer);
        createCustomer.setCreated(java.time.LocalDateTime.now());
        createCustomer.setModified(java.time.LocalDateTime.now());
        customerRepository.save(createCustomer);
        return createCustomer.getId();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('FRONTOFFICE')")
    public void updateCustomer(Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("Customer with " + id + " has not been found thus can not be updated");
        }
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('FRONTOFFICE')")
//    todo set appropriate request handler
    public void partialUpdateCustomer(Long id, Map<String, String> fields) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("Customer with id " + id + " has not been found thus can not be updated");
        }
        Customer updateCustomer = customerRepository.findById(id).orElse(null);
        for (String field : fields.keySet()) {
            switch (field) {
                case "firstName" -> updateCustomer.setFirstName(fields.get(field));
                case "lastName" -> updateCustomer.setLastName(fields.get(field));
                case "phone" -> updateCustomer.setPhone(fields.get(field));
                case "email" -> updateCustomer.setEmail(fields.get(field));
                case "street" -> updateCustomer.setStreet(fields.get(field));
                case "city" -> updateCustomer.setCity(fields.get(field));
                case "zip" -> updateCustomer.setZip(fields.get(field));
            }
        }
        updateCustomer.setModified(java.time.LocalDateTime.now());
        customerRepository.save(updateCustomer);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("Customer with id " + id + " has not been found");
        }
        customerRepository.deleteById(id);
    }

}
