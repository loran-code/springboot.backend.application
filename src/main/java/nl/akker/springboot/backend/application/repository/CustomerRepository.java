package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Collection<Customer> findAllByLastname(String lastname);

    Boolean existsByLastname(String lastname);

    Customer findCustomerByLastname(String lastname);

    Boolean existsByEmail(String email);

    boolean existsByCarsLicensePlate(String licensePlate);

    Customer findCustomerByCarsLicensePlate(String licensePlate);

}
