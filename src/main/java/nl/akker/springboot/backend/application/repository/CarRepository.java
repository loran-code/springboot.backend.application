package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.tables.Car;
import nl.akker.springboot.backend.application.model.tables.Customer;
import nl.akker.springboot.backend.application.model.tables.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findCarByLicensePlate(String licensePlate);
    boolean existsByLicensePlate(String licensePlate);

//    long saveCarToCustomer(String licensePlate, String lastname, Car car, Customer customer);
}