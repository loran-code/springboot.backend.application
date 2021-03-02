package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Component;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;

import java.util.Collection;

public interface ComponentService {

    Collection<Component> getAllComponents();

    ReturnObject addComponentToWorkOrder(WorkOrder workorder);

    ReturnObject createComponent(Component component);

    void deleteComponent(Long id);
}
