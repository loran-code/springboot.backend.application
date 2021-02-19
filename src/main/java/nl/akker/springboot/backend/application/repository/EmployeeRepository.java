package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    Boolean existsByEmail(String email);
//    Boolean existsByUserName(String userName);
//    Optional<Employee> findByUserName(String userName);
}
