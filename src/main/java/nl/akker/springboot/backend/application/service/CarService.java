package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;

import java.util.Collection;
import java.util.Map;

public interface CarService {

    Collection<Car> getCars();
    Car getCarById(Long id);
    Car findCarByLicensePlate(String licensePlate);
    ReturnObject createCar(Car car);
    long saveCarToCustomer(String licensePlate, String lastname, Car car, Customer customer);
    ReturnObject updateCar(Long id, Car car);
    long partialUpdateCar(Long id, Map<String, String> fields);
    void deleteCar(Long id);
}
