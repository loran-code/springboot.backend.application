package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Activity;

import java.util.Collection;

public interface ActivityService {

    Collection<Activity> getAllActivities();
    Activity findByActivityNumber(int activityNumber);
    Activity findByDescription(String description);
    ReturnObject addActivityToWorkOrder(Activity activity);
}
