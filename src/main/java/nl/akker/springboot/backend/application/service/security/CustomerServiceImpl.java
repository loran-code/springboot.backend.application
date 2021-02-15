package nl.akker.springboot.backend.application.service.security;

import nl.akker.springboot.backend.application.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Override
    public List<Customer> listAll() {
        return null;
    }

    @Override
    public Customer getById(long id) {
        return null;
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Customer saveOrUpdateCustomerForm(Customer customer) {
        return null;
    }
}
