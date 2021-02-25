package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Component;
import nl.akker.springboot.backend.application.service.ComponentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/component/")
public class ComponentController {

    private final ComponentService componentService;

    @GetMapping(path = "all")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_BACKOFFICE')")
    public Collection<Component> getComponentsCars() {
        return componentService.getAllComponents();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC')")
    public ResponseEntity<Object> addComponentsToWorkOrder(@RequestBody @Valid Component component) {
        ReturnObject returnObject = componentService.addComponentToWorkOrder(component);
        return ResponseEntity.ok().body(returnObject);
    }
}
