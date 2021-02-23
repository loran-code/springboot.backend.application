package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.tables.Car;
import nl.akker.springboot.backend.application.model.tables.Component;
import nl.akker.springboot.backend.application.service.ComponentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/component/")
public class ComponentController {

    private final ComponentService componentService;

    @GetMapping(path = "all")
    public Collection<Component> getComponentsCars() {
        return componentService.getAllComponents();
    }

    @PostMapping
    public ResponseEntity<Object> addComponentsToWorkOrder(@RequestBody @Valid Component component) {
        componentService.addComponentToWorkOrder(component);
        return ResponseEntity.ok().body("New component(s): " + component  + " has been added to the work order");
    }
}
