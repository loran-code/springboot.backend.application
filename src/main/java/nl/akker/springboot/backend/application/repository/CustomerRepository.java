package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Collection<Customer> findAllByLastName(String lastName);
}
