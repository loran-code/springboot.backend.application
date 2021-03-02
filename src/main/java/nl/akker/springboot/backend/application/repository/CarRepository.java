package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findCarByLicensePlate(String licensePlate);
    boolean existsByLicensePlate(String licensePlate);
}