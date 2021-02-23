package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.tables.Car;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface CarService {

    Collection<Car> getCars();
    Car getCarById(Long id);
    Car findCarByLicensePlate(String licensePlate);
    long createCar(Car car);
    long saveCarToCustomer(String licensePlate, String lastname);
    long updateCar(Long id, Car car);
    long partialUpdateCar(Long id, Map<String, String> fields);
    void deleteCar(Long id);
}
