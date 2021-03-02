package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Activity;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.repository.ActivityRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final WorkOrderRepository workOrderRepository;

    @Override
    public Collection<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public ReturnObject createActivity(Activity activity) {
        ReturnObject returnObject = new ReturnObject();

        if (activity != null) {
            Activity lastActivity = activityRepository.findTopByOrderByActivityNumberDesc();
            activity.setActivityNumber(lastActivity.getActivityNumber() + 1);

            activity.setDescription(activity.getDescription());
            activity.setPrice(activity.getPrice());
            activity.setCreated(LocalDateTime.now());
            activity.setModified(LocalDateTime.now());
            activityRepository.save(activity);

            returnObject.setObject(activity);
            returnObject.setMessage("New activity has been created");
        }

        returnObject.setMessage("The activity with the specified description already exists");

        return returnObject;
    }

    @Override
    public ReturnObject addActivityToWorkOrder(WorkOrder workorder) {
        ReturnObject returnObject = new ReturnObject();

        if (workorder != null) {
            WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workorder.getWorkOrderNumber());
            List<Activity> activities = updateWorkOrder.getActivities();

            for (Activity activity : workorder.getActivities()) {
                if (!activityRepository.existsByActivityNumber(activity.getActivityNumber())) {
                    throw new NotFoundException("One of the specified activities does not exist, make sure the correct activity number(s) have been given");
                }

                Activity updateActivity = activityRepository.findByActivityNumber(activity.getActivityNumber());
                activities.add(updateActivity);
            }

            updateWorkOrder.setActivities(activities);
            updateWorkOrder.setModified(java.time.LocalDateTime.now());
            workOrderRepository.save(updateWorkOrder);

            returnObject.setObject(updateWorkOrder);
            returnObject.setMessage("Activity added to work order");

            return returnObject;
        }
        returnObject.setMessage("Could not add activity with the specified details to the work order");

        return returnObject;
    }

    @Override
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new ApiRequestException("Activity with id " + id + " has not been found");
        }
        activityRepository.deleteById(id);
    }
}
