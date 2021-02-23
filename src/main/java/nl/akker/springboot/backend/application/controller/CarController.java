package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.tables.Car;
import nl.akker.springboot.backend.application.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/car/")
public class CarController {
    
    private final CarService carService;

    @GetMapping(path = "all")
    public Collection<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping(path = "{id}")
    public Car getCar(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }

    @GetMapping
    public Car findCarByLicensePlate(@RequestParam(name = "licenseplate") @Valid String licensePlate){
        return carService.findCarByLicensePlate(licensePlate);
    }

    @PostMapping
    public ResponseEntity<Object> registerNewCar(@RequestBody @Valid Car car) {
        carService.createCar(car);
        return ResponseEntity.ok().body("New car has been created: " + car);
    }

//    @PostMapping("/{car_id}/transfer/{customer_id}")
//    public ResponseEntity<Object> transferCarToCustomer(@PathVariable("car_id") long carId,
//                                                        @PathVariable("customer_id") long customerId,
//                                                        @RequestBody String licensePlate, String lastname) {
//        carService.saveCarToCustomer(licensePlate, lastname);
//        return ResponseEntity.ok().body("Car with licensePlate " + licensePlate + " has been added to customer " + lastname);
//    }


    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateCar(@PathVariable("id") Long id, @RequestBody @Valid Car car) {
        carService.updateCar(id, car);
        return ResponseEntity.ok().body("The car details have been updated: " + car);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> partiallyUpdateCar(@PathVariable("id") Long id, @RequestBody @Valid Map<String, String> fields) {
        carService.partialUpdateCar(id, fields);
        return ResponseEntity.ok().body("The specified Car details have been updated: " + fields);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().body("The Car with id " + id + " has been deleted");
    }
}
