package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

public interface CustomerService {

    Collection<Customer> getCustomers();

    Customer getCustomerById(Long id);

    Collection<Customer> getCustomersByLastname(String lastName);

    ReturnObject createCustomer(Customer customer);

    ReturnObject updateCustomer(Long id, Customer customer);

    ReturnObject partialUpdateCustomer(Long id, Map<String, String> fields);

    ResponseEntity<MessageResponse> addCarPapers(MultipartFile file, String licensePlate,String fileFormat);

    void deleteCustomer(Long id);
}
