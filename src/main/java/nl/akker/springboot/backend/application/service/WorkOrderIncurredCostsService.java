package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.dbmodels.Activity;
import nl.akker.springboot.backend.application.model.dbmodels.Component;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrderIncurredCosts;
import org.hibernate.jdbc.Work;

import java.util.Collection;
import java.util.List;

public interface WorkOrderIncurredCostsService {


    void AddActivity(Activity activity, WorkOrder workorder);

    void AddComponent(Component component, WorkOrder workorder);

    void deleteCost(Long id);

//    void AddCarCheck(WorkOrder workorder);

}
