package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.dbmodels.Component;
import nl.akker.springboot.backend.application.repository.ComponentsRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ComponentServiceImpl implements ComponentService {

    private final ComponentsRepository componentsRepository;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_BACKOFFICE')")
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

    //todo needs to be saved to work order
    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC')")
    public long addComponentToWorkOrder(Component component) {
        Component createComponent = componentsRepository.save(component);
        createComponent.setCreated(java.time.LocalDateTime.now());
        createComponent.setModified(java.time.LocalDateTime.now());
        componentsRepository.save(createComponent);

        return createComponent.getId();
    }
}
