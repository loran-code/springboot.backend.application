package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.repository.CarRepository;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CustomerRepository customerRepository;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public Collection<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with id " + id + " not found"));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public Car findCarByLicensePlate(String licensePlate) {
        if (!carRepository.existsByLicensePlate(licensePlate)) {
            throw new NotFoundException("The specified license plate " + licensePlate + " has not been found");
        }
        return carRepository.findCarByLicensePlate(licensePlate);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public long createCar(Car car) {
        Car createCar = carRepository.save(car);
        createCar.setCreated(java.time.LocalDateTime.now());
        createCar.setModified(java.time.LocalDateTime.now());
        carRepository.save(createCar);
        return createCar.getId();
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public long saveCarToCustomer(String licensePlate, String lastname, Car car, Customer customer) {

        customer = customerRepository.findCustomerByLastname(lastname);
        car = carRepository.findCarByLicensePlate(licensePlate);

        if (customer != null && car != null) {
//            todo save car to a customer
//            customer.setCar((List<Car>) getCarById(car.getId()));
            customer.setModified(java.time.LocalDateTime.now());
            customerRepository.save(customer);
            return car.getId();
        }
        throw new NotFoundException("The combination of lastname" + lastname + " license plate " + licensePlate + " has not been found");
    }


    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC')")
    public long updateCar(Long id, Car car) {
        if (!carRepository.existsById(id)) {
            throw new ApiRequestException("Car with " + id + " has not been found thus can not be updated");
        }
        Car updateCar = carRepository.findById(id).orElse(null);
        updateCar.setLicensePlate(car.getLicensePlate());
        updateCar.setModified(java.time.LocalDateTime.now());
        carRepository.save(updateCar);
        return updateCar.getId();
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC')")
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
        carRepository.deleteById(id);
    }
}
