package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.tables.Car;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface CarService {

    Collection<Car> getCars();
    Car getCarById(Long id);
    Collection<Car> findCarByLicensePlate(String licensePlate);
    long createCar(Car car);
    void updateCar(Long id, Car car);
    void partialUpdateCar(Long id, Map<String, String> fields);
    void deleteCar(Long id);
}
