package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public Collection<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public Car getCar(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public Car findCarByLicensePlate(@RequestParam(name = "licenseplate") @Valid String licensePlate){
        return carService.findCarByLicensePlate(licensePlate);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> registerNewCar(@RequestBody @Valid Car car) {
        carService.createCar(car);
        return ResponseEntity.ok().body("New car has been created: " + car);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC')")
    public ResponseEntity<Object> updateCar(@PathVariable("id") Long id, @RequestBody @Valid Car car) {
        carService.updateCar(id, car);
        return ResponseEntity.ok().body("The car details have been updated: " + car);
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC')")
    public ResponseEntity<Object> partiallyUpdateCar(@PathVariable("id") Long id, @RequestBody @Valid Map<String, String> fields) {
        carService.partialUpdateCar(id, fields);
        return ResponseEntity.ok().body("The specified Car details have been updated: " + fields);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().body("The Car with id " + id + " has been deleted");
    }
}
