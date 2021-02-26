package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.*;
import nl.akker.springboot.backend.application.repository.ComponentsRepository;
import nl.akker.springboot.backend.application.repository.InventoryRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderIncurredCostsRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderRepository;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComponentServiceImpl implements ComponentService {

    private final ComponentsRepository componentsRepository;
    private final WorkOrderRepository workOrderRepository;
    private final WorkOrderIncurredCostsRepository workOrderIncurredCostsRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public Collection<Component> getAllComponents() {
        return componentsRepository.findAll();
    }

    @Override
    public Component getRowNumber(int componentNumber) {
        Component component = componentsRepository.findByComponentNumber(componentNumber);

        return component;
    }

    @Override
    public Component findByDescription(String description) {
        Component component = componentsRepository.findByDescription(description);

        return component;
    }

    @Override
    public ReturnObject addComponentToWorkOrder(Long workOrderNumber, WorkOrder workorder) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number does not exist");
        }

        ReturnObject returnObject = new ReturnObject();
        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);
        List<Component> components = new ArrayList<>();

        for (Component component : workorder.getComponents()) {
            if (!componentsRepository.existsByDescription(component.getDescription())) {
                throw new NotFoundException("One of the specified components does not exist, make sure the correct description(s) have been given");
            }

            Component componentToAdd = componentsRepository.findByDescription(component.getDescription());
            components.add(componentToAdd);

            Inventory updateInventory = inventoryRepository.findByDescription(component.getDescription());

            if ((updateInventory.getStockAmount() - component.getAmount()) <= 0) {
                throw new ApiRequestException("Can not add the component(s) to the work order as there is not enough stock left in the inventory");
            }

            updateInventory.setStockAmount(updateInventory.getStockAmount() - component.getAmount());
            updateInventory.setModified(LocalDateTime.now());
            inventoryRepository.save(updateInventory);
        }

        updateWorkOrder.setComponents(components);
        updateWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);

        returnObject.setObject(updateWorkOrder);
        returnObject.setMessage(components.size() + " component(s) added to work order and have been deducted from the inventory");

        return returnObject;
    }

    public ReturnObject createComponent(Component component) {
        if (componentsRepository.existsByDescription(component.getDescription())) {
            throw new ApiRequestException("The component with the specified description already exists");
        }
        ReturnObject returnObject = new ReturnObject();

        Component latestComponent = componentsRepository.findTopByOrderByComponentNumberDesc();
        component.setComponentNumber(latestComponent.getComponentNumber() + 1);

        component.setComponentNumber(component.getComponentNumber());
        component.setDescription(component.getDescription());
        component.setPrice(component.getPrice());
        component.setCreated(LocalDateTime.now());
        component.setModified(LocalDateTime.now());
        componentsRepository.save(component);

        returnObject.setObject(component);
        returnObject.setMessage("New component has been created");

        return returnObject;
    }


    @Override
    public void deleteComponent(Long id) {
        if (!componentsRepository.existsById(id)) {
            throw new ApiRequestException("Component with id " + id + " has not been found");
        }
        componentsRepository.deleteById(id);
    }
}

