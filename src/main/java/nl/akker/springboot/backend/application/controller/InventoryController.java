package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Inventory;
import nl.akker.springboot.backend.application.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/inventory/")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping(path = "all")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_BACKOFFICE')")
    public Collection<Inventory> getInventoryItems() {
        return inventoryService.getAllComponents();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> addComponentToInventory(@Valid @RequestBody Inventory inventory) {
        ReturnObject returnObject = inventoryService.addComponentToInventory(inventory);
        return ResponseEntity.ok().body(returnObject);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteWorkOrder(@PathVariable("id") Long id) {
        inventoryService.deleteComponentFromInventory(id);
        return ResponseEntity.ok().body("The inventory item with id " + id + " has been deleted");
    }
}
