package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// Needed in order to test against the H2 database.
@DataJpaTest
class CustomerServiceImplTest {


    @Autowired
    private CustomerRepository underTestRepository;


    @Test
    void itShouldGetAllCustomers() {

        // Given
        Customer customer1 = new Customer("Eric", "Goossens", "06-23584829",
                "goossens@mail.com", "loopstraat",
                "Dokkum", "3029CJ", false
        );
        Customer customer2 = new Customer("Karien", "Staal", "06-23586720",
                "staal@mail.com", "staalstraat",
                "Duiven", "9853KR", false
        );

        underTestRepository.save(customer1);
        underTestRepository.save(customer2);

        // When
        Collection<Customer> allCustomers = underTestRepository.findAll();

        // Then
        assertEquals(2, allCustomers.size());
    }

    @Test
    void itShouldGetCustomerByEmail() {
        //Given
        Customer customer = new Customer("Eric",
                "Goossens", "06-23584829",
                "goossens@mail.com", "Loopstraat",
                "Dokkum", "3029CJ", false
        );

        underTestRepository.save(customer);

        // When
        Customer customerFoundById = underTestRepository.findByEmail("goossens@mail.com");

        // Then
        assertEquals("Eric", customerFoundById.getFirstname());
        assertEquals("Goossens", customerFoundById.getLastname());
        assertEquals("06-23584829", customerFoundById.getPhone());
        assertEquals("goossens@mail.com", customerFoundById.getEmail());
        assertEquals("Loopstraat", customerFoundById.getStreet());
        assertEquals("Dokkum", customerFoundById.getCity());
        assertEquals("3029CJ", customerFoundById.getZip());
    }

    @Test
    void itShouldGetCustomerByLastName() {
        //Given
        Customer customer = new Customer("Eric",
                "Goossens", "06-23584829",
                "goossens@mail.com", "loopstraat",
                "Dokkum", "3029CJ", false
        );

        underTestRepository.save(customer);

        //When
        Optional<Customer> customerFoundByLastname = underTestRepository.findOptionalCustomerByLastname("Goossens");

        //Then
        assertEquals("Goossens", customerFoundByLastname.get().getLastname());
    }

    @Test
    void itShouldSaveCustomer() {
        //Given
        Customer customer = new Customer(1L,"Eric",
                "Goossens", "06-23584829",
                "goossens@mail.com", "loopstraat",
                "Dokkum", "3029CJ", false
        );

        underTestRepository.save(customer);

        //When
        Optional<Customer> optionalCustomer = underTestRepository.existsById(1L);

        //Then
        assertThat(optionalCustomer).isPresent();
    }

    @Test
    void deleteCustomerByEmail() {
        // Given
        Customer customer1 = new Customer(1L,"Eric", "Goossens", "06-23584829",
                "goossens@mail.com", "loopstraat",
                "Dokkum", "3029CJ", false
        );
        Customer customer2 = new Customer(2L,"Karien", "Staal", "06-23586720",
                "staal@mail.com", "staalstraat",
                "Duiven", "9853KR", false
        );

        underTestRepository.save(customer1);
        underTestRepository.save(customer2);

        //When
        underTestRepository.deleteAll();

        // Then
        Collection<Customer> customers = underTestRepository.findAll();
        assertEquals(0, customers.size());
    }
}
