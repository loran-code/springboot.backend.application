package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Collection<Customer> findAllByLastName(String name);

    @Query("SELECT c FROM Customer c WHERE c.firstName = ?1")
    Optional<Customer> findCustomerByFirstName(String name);
}
