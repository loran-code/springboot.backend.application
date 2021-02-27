package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;
import nl.akker.springboot.backend.application.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/customer/")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "all")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public Collection<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public Collection<Customer> getCustomersByLastname(@RequestParam(name = "lastname") String lastname) {
        return customerService.getCustomersByLastname(lastname);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> registerNewCustomer(@RequestBody @Valid Customer customer) {
        ReturnObject returnObject = customerService.createCustomer(customer);
        return ResponseEntity.ok().body(returnObject);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long id, @RequestBody @Valid Customer customer) {
        ReturnObject returnObject = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok().body(returnObject);
    }

    @PatchMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> partiallyUpdateCustomer(@PathVariable("id") Long id, @RequestBody @Valid Map<String, String> fields) {
        customerService.partialUpdateCustomer(id, fields);
        return ResponseEntity.ok().body("The specified customer details have been updated: " + fields);
    }

    @PostMapping(path = "upload")
//    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
//    produces = {MediaType.IMAGE_JPEG_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRONTOFFICE')")
    public ResponseEntity<MessageResponse> uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("licensePlate") String licensePlate) {
        return customerService.addCarPapers(file, licensePlate);
    }


    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body("The customer with id " + id + " has been deleted");
    }
}
