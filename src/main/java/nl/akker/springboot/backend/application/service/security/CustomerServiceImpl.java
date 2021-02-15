package nl.akker.springboot.backend.application.service.security;

import nl.akker.springboot.backend.application.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    public List<Customer> getCustomers() {
        return List.of(
                new Customer(
                        1L,
                        "Loran",
                        "Akker",
                        "06-12345678",
                        "loran@email.com",
                        "Sesamstraat",
                        "Den Haag",
                        "2574AV",
                        java.time.LocalDateTime.now(),
                        java.time.LocalDateTime.now())
        );
    }


    @Override
    public long createCustomer(Customer customer) {
        return 0;
    }

    @Override
    public void updateCustomer(long id, Customer customer) {

    }

    @Override
    public void deleteCustomer(long id) {

    }

    @Override
    public List<Customer> getCustomersByLastName(String name) {
        return null;
    }

    @Override
    public Optional<Customer> getCustomerById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean customerExistsById(long id) {
        return false;
    }
}