package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerRepository customerRepository;
    private CustomerServiceImpl underTest;
    private java.util.List<Car> List;

    @BeforeEach
    void setUp() {
//        underTest = new CustomerServiceImpl(customerRepository);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void getCustomers() {

        // Given
        Customer customer1 = new Customer(1L,"Eric", "Goossens", "06-23584829",
                "goossens@mail.com", "loopstraat",
                "Dokkum", "3029CJ", false, LocalDateTime.now(), LocalDateTime.now(), List);
        Customer customer2 = new Customer(2L, "Karien", "Staal", "06-23586720",
                "staal@mail.com", "staalstraat",
                "Duiven", "9853KR", false, LocalDateTime.now(), LocalDateTime.now(), List);

        customerRepository.saveAll(Arrays.asList(customer1, customer2));

        // When
        Collection<Customer> customers = underTest.getCustomers();

        // Then
        assertEquals(2, customers.size());
    }

    @Test
    void getCustomerById() {
        //given
        Customer customer1 = new Customer(1L,"Eric", "Goossens", "06-23584829",
                "goossens@mail.com", "loopstraat",
                "Dokkum", "3029CJ", false, LocalDateTime.now(), LocalDateTime.now(), List);

        customerRepository.save(customer1);

        // When
        Customer actual = underTest.getCustomerById(1L);

        // Then
        assertEquals(1L, actual.getId());
        assertEquals("Eric", actual.getFirstname());
        assertEquals("Goossens", actual.getLastname());
        assertEquals("06-23584829", actual.getPhone());
        assertEquals("gossens@mail.com", actual.getEmail());
        assertEquals("Loopstraat", actual.getStreet());
        assertEquals("Dokkum", actual.getCity());
        assertEquals("3029CJ", actual.getZip());
    }

//    @Test
//    void deleteCustomer() {
//        @Test
//        public void whenOrderRequestIsDeleted_thenDeleteShipmentInfo() {
//            createOrderRequestWithShipmentInfo();
//
//            OrderRequest orderRequest = entityManager.find(OrderRequest.class, 1L);
//
//            entityManager.getTransaction().begin();
//            entityManager.remove(orderRequest);
//            entityManager.getTransaction().commit();
//
//            Assert.assertEquals(0, findAllOrderRequest().size());
//            Assert.assertEquals(0, findAllShipmentInfo().size());
//        }
//
//        private void createOrderRequestWithShipmentInfo() {
//            ShipmentInfo shipmentInfo = new ShipmentInfo("name");
//            OrderRequest orderRequest = new OrderRequest(shipmentInfo);
//
//            entityManager.getTransaction().begin();
//            entityManager.persist(orderRequest);
//            entityManager.getTransaction().commit();
//
//            Assert.assertEquals(1, findAllOrderRequest().size());
//            Assert.assertEquals(1, findAllShipmentInfo().size());
//        }
//    }
}