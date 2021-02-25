package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Inventory;
import nl.akker.springboot.backend.application.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Collection<Inventory> getAllComponents() {
        return inventoryRepository.findAll();
    }

    @Override
    public ReturnObject addComponentToInventory(Inventory inventory) {
        ReturnObject returnObject = new ReturnObject();

        if (inventory != null) {
            Inventory createInventoryComponent = inventory;

            Inventory latestInventoryItem = inventoryRepository.findTopByOrderByCreatedDesc();
            createInventoryComponent.setInventoryNumber(latestInventoryItem.getInventoryNumber() + 1);

            createInventoryComponent.setDescription(inventory.getDescription());
            createInventoryComponent.setStockAmount(inventory.getStockAmount());
            createInventoryComponent.setCreated(LocalDateTime.now());
            createInventoryComponent.setModified(LocalDateTime.now());
            inventoryRepository.save(createInventoryComponent);

            returnObject.setObject(createInventoryComponent);
            returnObject.setMessage("Inventory item has been created");

            return returnObject;
        }
        returnObject.setMessage("Could not create an inventory item with the specified details");

        return returnObject;
    }

    @Override
    public void deleteComponentFromInventory(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new ApiRequestException("Inventory item with id " + id + " has not been found");
        }
        inventoryRepository.deleteById(id);
    }
}
