package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.dbmodels.Component;

import java.util.Collection;

public interface ComponentService {

    Collection<Component> getAllComponents();
    Component getRowNumber(int componentNumber);
    Component findByDescription(String description);
    long addComponentToWorkOrder(Component component);

}
