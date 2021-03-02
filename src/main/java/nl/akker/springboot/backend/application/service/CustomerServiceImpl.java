package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;
import nl.akker.springboot.backend.application.repository.CarRepository;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

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
    public ReturnObject createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ApiRequestException("The specified details are already taken. Make sure your email is unique");
        }
        ReturnObject returnObject = new ReturnObject();

        Customer createCustomer = customer;
        createCustomer.setCreated(LocalDateTime.now());
        createCustomer.setModified(LocalDateTime.now());
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
        updateCustomer.setModified(LocalDateTime.now());
        customerRepository.save(updateCustomer);

        returnObject.setObject(updateCustomer);
        returnObject.setMessage("Customer has been updated");

        return returnObject;
    }

    @Override
    public ReturnObject partialUpdateCustomer(Long id, Map<String, String> fields) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("Customer with id " + id + " has not been found thus can not be updated");
        }
        ReturnObject returnObject = new ReturnObject();
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
        updateCustomer.setModified(LocalDateTime.now());
        customerRepository.save(updateCustomer);

        returnObject.setMessage("The customers information has been updated");
        returnObject.setObject(updateCustomer);
        return returnObject;
    }

    @Override
    public ResponseEntity<MessageResponse> addCarPapers(MultipartFile file, String licensePlate, String fileFormat) {
        if (null == file.getOriginalFilename()) {
            return ResponseEntity.ok(new MessageResponse("Failed to add car papers."));

        } else if (!carRepository.existsByLicensePlate(licensePlate)) {
            throw new ApiRequestException("The specified license plate does not exist");

        } else if (!customerRepository.existsByCarsLicensePlate(licensePlate)) {
            throw new ApiRequestException("The car does not belong to a customer. Make sure the car has been added to a customer");

        } else {

            String folder = "src/main/resources/static/carpapers/";

            try {
                byte[] bytes = file.getBytes();
                // Specify location for the file to be saved at
                Path path = Paths.get(folder + licensePlate + fileFormat);
                // write the uploaded file to the root location
                Files.write(path, bytes);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            Customer customer = customerRepository.findCustomerByCarsLicensePlate(licensePlate);
            customer.setCarPapers(true);
            customerRepository.save(customer);

            return ResponseEntity.ok(new MessageResponse("Car papers for customer " + customer.getFirstname() + " " + customer.getLastname() + " have been added to location: " + folder));
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ApiRequestException("Customer with id " + id + " has not been found");
        }
        customerRepository.deleteById(id);
    }
}
