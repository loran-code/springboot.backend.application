package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Boolean existsByUsername(String userName);
    Boolean existsByEmail(String email);
    Optional<Employee> findByUsername(String userName);
    Employee findEmployeeByUsername(String userName);
    Employee findEmployeeByEmail(String email);

}
