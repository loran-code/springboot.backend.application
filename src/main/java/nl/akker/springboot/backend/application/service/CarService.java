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

    ReturnObject saveCarToCustomer(Customer customer);

    ReturnObject updateCar(Long id, Car car);

    ReturnObject partialUpdateCar(Long id, Map<String, String> fields);

    void deleteCar(Long id);
}
