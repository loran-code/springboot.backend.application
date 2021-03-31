package nl.akker.springboot.backend.application.controller;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    
    @Mock
    private CustomerService underTestService;
    
    @InjectMocks
    CustomerController controller;

    List<Customer> customers;

    Customer customer1;

    ReturnObject returnObject;

    MockMvc mockMvc;
    
    @BeforeEach
    void setUp() {
        customers = new ArrayList<>();

        customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Jan");
        customer1.setLastname("Peters");
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstname("Gerard");
        customer2.setLastname("Govers");
        customer2.setId(2L);
        customers.add(customer2);

        Car car1 = new Car();
        car1.setCustomer(customer1);
        car1.setLicensePlate("AA-111-AA");

        returnObject  = new ReturnObject();
        returnObject.setObject(customer1);
        returnObject.setMessage("Succes!");

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void itShouldGetEndpointCustomers() throws Exception {
        //Given
        int expected = 2;
        when(underTestService.getCustomers()).thenReturn(customers);


        //When
        mockMvc.perform(get("/api/customer/all"))
                .andExpect(status().isOk());

        //Then
        assertEquals(expected, customers.size());
    }

    @Test
    @WithMockUser
    void itShouldgetEndpointCustomerByLastname() throws Exception {
        //Given
        String expected = "Peters";

        //When
        when(underTestService.getCustomerByLastname(expected)).thenReturn(customer1);
        mockMvc.perform(get("/api/customer/?lastname=Peters"))
                .andExpect(status().isOk());

        //Then
        assertEquals(expected, customer1.getLastname());
    }
}