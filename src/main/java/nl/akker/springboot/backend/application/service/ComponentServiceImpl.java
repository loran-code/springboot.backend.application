package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Component;
import nl.akker.springboot.backend.application.repository.ComponentsRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderIncurredCostsRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ComponentServiceImpl implements ComponentService {

    private final ComponentsRepository componentsRepository;
    private final WorkOrderIncurredCostsRepository workOrderIncurredCostsRepository;

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

    //todo needs to be saved to work order incurred costs
    @Override
    public ReturnObject addComponentToWorkOrder(Component component) {
        ReturnObject returnObject = new ReturnObject();

        if (component != null) {
            Component createComponent = component;
            createComponent.setCreated(java.time.LocalDateTime.now());
            createComponent.setModified(java.time.LocalDateTime.now());
            componentsRepository.save(createComponent);

            returnObject.setObject(createComponent);
            returnObject.setMessage("Component(s) added to work order");

            return returnObject;
        }
        returnObject.setMessage("Could not add component with the specified details to the work order");

        return returnObject;
    }
}
