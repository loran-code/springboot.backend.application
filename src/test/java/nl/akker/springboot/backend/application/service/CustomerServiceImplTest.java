//package nl.akker.springboot.backend.application.service;
//
//import nl.akker.springboot.backend.application.model.dbmodels.Car;
//import nl.akker.springboot.backend.application.model.dbmodels.Customer;
//import nl.akker.springboot.backend.application.repository.CustomerRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//import javax.swing.text.StyledEditorKit;
//import java.time.LocalDateTime;
//import java.util.*;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//class CustomerServiceImplTest {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    private CustomerService customerService;
//    List<Car> cars;
//
//
//
////    @BeforeEach
////    void setUp() {
////        underTest = new CustomerServiceImpl(customerRepository);
////    }
//
//
////    @AfterEach
////    void tearDown() {
////        underTestRepository.deleteAll();
////    }
//
//    @Test
//    void itShouldGetCustomers() {
//
//        // Given
//        Customer customer1 = new Customer(1L,"Eric", "Goossens", "06-23584829",
//                "goossens@mail.com", "loopstraat",
//                "Dokkum", "3029CJ", false, LocalDateTime.now(), LocalDateTime.now(), cars);
//        Customer customer2 = new Customer(2L, "Karien", "Staal", "06-23586720",
//                "staal@mail.com", "staalstraat",
//                "Duiven", "9853KR", false, LocalDateTime.now(), LocalDateTime.now(), cars);
//
//        customerRepository.save(customer1);
//        customerRepository.save(customer2);
//
//        // When
//        Collection<Customer> customers = customerRepository.findAll();
//
//        // Then
//        assertEquals(2, customers.size());
//    }
//
//    @Test
//    void itShouldGetCustomerById() {
//        //given
//        Customer customer = new Customer(1L,"Eric", "Goossens", "06-23584829",
//                "goossens@mail.com", "loopstraat",
//                "Dokkum", "3029CJ", false, LocalDateTime.now(), LocalDateTime.now(), cars);
//
//        customerRepository.save(customer);
//
//        // When
//        Customer optionalCustomer = customerService.getCustomerById(1L);
//
//        // Then
//        assertEquals(1L, optionalCustomer.getId());
//        assertEquals("Eric", optionalCustomer.getFirstname());
//        assertEquals("Goossens", optionalCustomer.getLastname());
//        assertEquals("06-23584829", optionalCustomer.getPhone());
//        assertEquals("gossens@mail.com", optionalCustomer.getEmail());
//        assertEquals("Loopstraat", optionalCustomer.getStreet());
//        assertEquals("Dokkum", optionalCustomer.getCity());
//        assertEquals("3029CJ", optionalCustomer.getZip());
//    }
//
//
////    @Test
////    void itShouldGetCustomerByLastName() {
////
////    }
//
////    @Test
////    void itShouldSaveCustomer() {
////        //Given
////        Customer customer = new Customer(1L,"Eric",
////                "Goossens", "06-23584829",
////                "goossens@mail.com", "loopstraat",
////                "Dokkum", "3029CJ", false,
////                LocalDateTime.now(), LocalDateTime.now(), cars);
////
////        //When
////        customerRepository.save(customer);
////
////        //Then
////        Optional<Customer> optionalCustomer = customerRepository.findById(1L);
////        assertThat(optionalCustomer).isPresent();
////    }
//
//
//    //    @Test
////    void deleteCustomer() {
////        @Test
////        public void whenOrderRequestIsDeleted_thenDeleteShipmentInfo() {
////            createOrderRequestWithShipmentInfo();
////
////            OrderRequest orderRequest = entityManager.find(OrderRequest.class, 1L);
////
////            entityManager.getTransaction().begin();
////            entityManager.remove(orderRequest);
////            entityManager.getTransaction().commit();
////
////            Assert.assertEquals(0, findAllOrderRequest().size());
////            Assert.assertEquals(0, findAllShipmentInfo().size());
////        }
////
////        private void createOrderRequestWithShipmentInfo() {
////            ShipmentInfo shipmentInfo = new ShipmentInfo("name");
////            OrderRequest orderRequest = new OrderRequest(shipmentInfo);
////
////            entityManager.getTransaction().begin();
////            entityManager.persist(orderRequest);
////            entityManager.getTransaction().commit();
////
////            Assert.assertEquals(1, findAllOrderRequest().size());
////            Assert.assertEquals(1, findAllShipmentInfo().size());
////        }
////    }
//}