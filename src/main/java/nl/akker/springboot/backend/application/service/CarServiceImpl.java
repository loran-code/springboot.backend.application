package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.Component;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.repository.CarRepository;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CustomerRepository customerRepository;

    @Override
    public Collection<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with id " + id + " not found"));
    }

    @Override
    public Car findCarByLicensePlate(String licensePlate) {
        if (!carRepository.existsByLicensePlate(licensePlate)) {
            throw new NotFoundException("The specified license plate " + licensePlate + " has not been found");
        }
        return carRepository.findCarByLicensePlate(licensePlate);
    }

    @Override
    public ReturnObject createCar(Car car) {
        if (carRepository.existsByLicensePlate(car.getLicensePlate())) {
            throw new ApiRequestException("The specified license plate is already taken. Make sure the license plate details are unique");
        }
        ReturnObject returnObject = new ReturnObject();

        Car createCar = car;

        createCar.setLicensePlate(car.getLicensePlate());
        createCar.setCreated(java.time.LocalDateTime.now());
        createCar.setModified(java.time.LocalDateTime.now());
        carRepository.save(createCar);

        returnObject.setObject(createCar);
        returnObject.setMessage("Car has been created");

        return returnObject;
    }

    @Override
    public ReturnObject saveCarToCustomer(Customer customer) {
        if (!customerRepository.existsByLastname(customer.getLastname())) {
            throw new ApiRequestException("The specified details are wrong. Make sure the customer with the given lastname exists.");
        }
        ReturnObject returnObject = new ReturnObject();
        List<Car> cars = new ArrayList<>();
        Customer updateCustomer = customerRepository.findCustomerByLastname(customer.getLastname());

        for (Car car : customer.getCars()) {

            Car carToAdd = carRepository.findCarByLicensePlate(car.getLicensePlate());

            if (carToAdd != null) {
                cars.add(carToAdd);
            }
        }

        updateCustomer.setCars(cars);
        updateCustomer.setModified(java.time.LocalDateTime.now());
        customerRepository.save(updateCustomer);

        returnObject.setObject(updateCustomer);
        returnObject.setMessage("car(s) have been saved to customer: " + updateCustomer.getLastname());

        return returnObject;

    }


    @Override
    public ReturnObject updateCar(Long id, Car car) {
        if (!carRepository.existsById(id)) {
            throw new ApiRequestException("Car with " + id + " has not been found thus can not be updated");
        }
        ReturnObject returnObject = new ReturnObject();

        Car updateCar = carRepository.findById(id).orElse(null);
        updateCar.setLicensePlate(car.getLicensePlate());
        updateCar.setModified(java.time.LocalDateTime.now());
        carRepository.save(updateCar);

        returnObject.setObject(updateCar);
        returnObject.setMessage("car has been updated");

        return returnObject;
    }

    @Override
    public long partialUpdateCar(Long id, Map<String, String> fields) {
        if (!carRepository.existsById(id)) {
            throw new ApiRequestException("Car with id " + id + " has not been found thus can not be updated");
        }
        Car updateCar = carRepository.findById(id).orElse(null);
        for (String field : fields.keySet()) {
            switch (field) {
                case "license plate" -> updateCar.setLicensePlate(fields.get(field));
            }
            updateCar.setModified(java.time.LocalDateTime.now());
            carRepository.save(updateCar);
        }
        return updateCar.getId();
    }

    @Override
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new ApiRequestException("Car with id " + id + " has not been found");
        }
        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiRequestException("Can not delete car because it is attached to another entity. (customer / work order)");
        }
    }
}
