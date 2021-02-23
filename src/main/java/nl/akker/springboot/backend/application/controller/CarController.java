package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.tables.Car;
import nl.akker.springboot.backend.application.service.CarService;
import org.springframework.http.ResponseEntity;
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
    public Collection<Car> findCarByLicensePlate(@RequestParam(name = "licenseplate") @Valid String licensePlate){
        return carService.findCarByLicensePlate(licensePlate);
    }

    @PostMapping
    public ResponseEntity<Object> registerNewCar(@RequestBody @Valid Car Car) {
        carService.createCar(Car);
        return ResponseEntity.ok().body("New car has been created: " + Car);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateCar(@PathVariable("id") Long id, @RequestBody @Valid Car Car) {
        carService.updateCar(id, Car);
        return ResponseEntity.ok().body("The car details have been updated: " + Car);
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