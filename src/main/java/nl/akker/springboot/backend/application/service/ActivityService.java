package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Activity;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;

import java.util.Collection;

public interface ActivityService {

    Collection<Activity> getAllActivities();

    ReturnObject addActivityToWorkOrder(WorkOrder workorder);

    ReturnObject createActivity(Activity activity);

    void deleteActivity(Long id);
}
