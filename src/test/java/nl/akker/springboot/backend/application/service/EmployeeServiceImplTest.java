package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.dbmodels.Employee;
import nl.akker.springboot.backend.application.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@SpringBootTest()
@AutoConfigureMockMvc
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService underTest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
//        underTest = new EmployeeService(employeeRepository);

        Employee employee = new Employee("test_user", "test@mail", "test_password");

        Mockito.when(employeeRepository.findEmployeeByUsername(employee.getUsername())).thenReturn(employee);
        Mockito.when(employeeRepository.findEmployeeByEmail(employee.getEmail())).thenReturn(employee);

    }
//    @AfterEach
//    public void tearDown() {
//
//    }

    @Test
    void itShouldGetEmployeeByUserName() {
        //Given
        String expected = "test_user";

        //When
        Employee found = employeeRepository.findEmployeeByUsername("test_user");

        //Then
        assertEquals(expected, found.getUsername());
    }

//    @Test
//    void itShouldGetEmployeeById() {
//        //Given
//        //When
//        //Then
//    }
//
    @Test
    void itShouldCreateEmployee() {
        //Given

        //When

        //Then
    }
//
//    @Test
//    void itShouldUpdateEmployee() {
//        //Given
//        //When
//        //Then
//    }
//
//    @Test
//    void itShouldPartialUpdateEmployee() {
//        //Given
//        //When
//        //Then
//    }
//
//    @Test
//    void itShouldGetWorkOrdersByStatus() {
//        //Given
//        //When
//        //Then
//    }
//
//    @Test
//    void itShouldCallCustomersBasedOnWorkOrderStatus() {
//        //Given
//        //When
//        //Then
//    }
//
//    @Test
//    void itShouldDeleteEmployee() {
//        //Given
//        //When
//        //Then
//    }
}

