package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Inventory;

import java.util.Collection;

public interface InventoryService {

    Collection<Inventory> getAllComponents();

    ReturnObject addComponentToInventory(Inventory inventory);

    void deleteComponentFromInventory(Long id);
}
