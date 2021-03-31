package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.dbmodels.Employee;
import nl.akker.springboot.backend.application.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@ContextConfiguration(classes = {Employee.class})
class EmployeeServiceImplTest {

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository underTestRepository;

    @Mock
    Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee("test_user", "test@mail", "test_password");
        underTestRepository.save(employee);

        Employee employee2 = new Employee("test_user2", "test@mail2", "test_password2");
        underTestRepository.save(employee2);

        Mockito.when(underTestRepository.findEmployeeByUsername(employee.getUsername())).thenReturn(employee);
        Mockito.when(underTestRepository.findEmployeeByEmail(employee.getEmail())).thenReturn(employee);
    }

    @Test
    void itShouldGetEmployeeByUserName() {
        //Given
        String expected = "test_user";

        //When
        Employee found = underTestRepository.findEmployeeByUsername("test_user");

        //Then
        assertEquals(expected, found.getUsername());
    }

    @Test
    void itShouldGetEmployeeByEmail() {
        //Given
        String expected = "test@mail";

        //When
        Employee found = underTestRepository.findEmployeeByEmail("test@mail");

        //Then
        assertEquals(expected, found.getEmail());
    }

    @Test
    void itShouldGetEmployees() {
        //Given
        employee = new Employee("test_user", "test@mail", "test_password");
        underTestRepository.save(employee);

        Employee employee2 = new Employee("test_user2", "test@mail2", "test_password2");
        underTestRepository.save(employee2);
        int expected = 2;

        //When
        Collection<Employee> found = employeeService.getEmployees();
        found.add(employee);
        found.add(employee2);

        //Then
        assertEquals(expected, found.size());
    }
}

