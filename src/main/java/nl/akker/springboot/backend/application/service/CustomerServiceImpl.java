package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
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
                .orElseThrow(() -> new NotFoundException("Customer with id " + id + " not found"));
    }

    @Override
    public Collection<Customer> getCustomersByLastname(String lastname) {
        if (!customerRepository.existsByLastname(lastname)) {
            throw new NotFoundException("The specified last name " + lastname + " has not been found");
        }
        return customerRepository.findAllByLastname(lastname);
    }


    @Override
    //todo add car to customer
    public ReturnObject createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ApiRequestException("The specified details are already taken. Make sure your email is unique");
        }
        ReturnObject returnObject = new ReturnObject();

        Customer createCustomer = customer;
        createCustomer.setCreated(java.time.LocalDateTime.now());
        createCustomer.setModified(java.time.LocalDateTime.now());
        customerRepository.save(createCustomer);

        returnObject.setObject(createCustomer);
        returnObject.setMessage("Customer has been created");

        return returnObject;
    }

    @Override
    public ReturnObject updateCustomer(Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("Customer with " + id + " has not been found thus can not be updated");
        }
        ReturnObject returnObject = new ReturnObject();

        Customer updateCustomer = customerRepository.findById(id).orElse(null);
        updateCustomer.setFirstname(customer.getFirstname());
        updateCustomer.setLastname(customer.getLastname());
        updateCustomer.setPhone(customer.getPhone());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setCity(customer.getCity());
        updateCustomer.setZip(customer.getZip());
        updateCustomer.setModified(java.time.LocalDateTime.now());
        customerRepository.save(updateCustomer);

        returnObject.setObject(updateCustomer);
        returnObject.setMessage("Customer has been updated");

        return returnObject;
    }

    @Override
    public long partialUpdateCustomer(Long id, Map<String, String> fields) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("Customer with id " + id + " has not been found thus can not be updated");
        }

        Customer updateCustomer = customerRepository.findById(id).orElse(null);

        for (String field : fields.keySet()) {
            switch (field) {
                case "firstname" -> updateCustomer.setFirstname(fields.get(field));
                case "lastname" -> updateCustomer.setLastname(fields.get(field));
                case "phone" -> updateCustomer.setPhone(fields.get(field));
                case "email" -> updateCustomer.setEmail(fields.get(field));
                case "street" -> updateCustomer.setStreet(fields.get(field));
                case "city" -> updateCustomer.setCity(fields.get(field));
                case "zip" -> updateCustomer.setZip(fields.get(field));
            }
        }
        updateCustomer.setModified(java.time.LocalDateTime.now());
        customerRepository.save(updateCustomer);

        return updateCustomer.getId();
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("Customer with id " + id + " has not been found");
        }
        customerRepository.deleteById(id);
    }
}
