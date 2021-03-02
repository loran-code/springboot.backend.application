package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Component;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
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
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE', 'ROLE_BACKOFFICE')")
    public Collection<Component> getComponents() {
        return componentService.getAllComponents();
    }

    @PostMapping(path = "create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_BACKOFFICE')")
    public ResponseEntity<Object> createComponent(@RequestBody @Valid Component component) {
        ReturnObject returnObject = componentService.createComponent(component);
        return ResponseEntity.ok().body(returnObject);
    }

    @PostMapping(path = "add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC')")
    public ResponseEntity<Object> addComponentsToWorkOrder(@RequestBody @Valid WorkOrder workorder) {
        ReturnObject returnObject = componentService.addComponentToWorkOrder(workorder);
        return ResponseEntity.ok().body(returnObject);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_BACKOFFICE')")
    public ResponseEntity<Object> deleteComponent(@PathVariable("id") Long id) {
        componentService.deleteComponent(id);
        return ResponseEntity.ok().body("The component with id " + id + " has been deleted");
    }
}
