package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.tables.Activity;
import nl.akker.springboot.backend.application.model.tables.Component;

import java.util.Collection;

public interface ActivityService {

    Collection<Activity> getAllActivities();
    Activity findByActivityNumber(int activityNumber);
    Activity findByDescription(String description);
    long addActivityToWorkOrder(Activity activity);
}
