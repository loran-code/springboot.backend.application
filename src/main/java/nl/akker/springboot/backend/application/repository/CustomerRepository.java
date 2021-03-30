package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Collection<Customer> findAllByLastname(String lastname);

    Boolean existsByLastname(String lastname);

    Customer findCustomerByLastname(String lastname);

    Optional<Customer> existsById(long id);

    void deleteByIdEquals(long id);

    Customer findByEmail(String email);

    Customer deleteByEmail(String email);

    Optional<Customer> findOptionalCustomerByLastname(String lastname);

    Boolean existsByEmail(String email);

    boolean existsByCarsLicensePlate(String licensePlate);

    Customer findCustomerByCarsLicensePlate(String licensePlate);
}
