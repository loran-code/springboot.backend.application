package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.tables.Activity;
import nl.akker.springboot.backend.application.repository.ActivityRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_BACKOFFICE')")
    public Collection<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity findByActivityNumber(int activityNumber) {
        Activity activity = activityRepository.findByActivityNumber(activityNumber);

        return activity;
    }

    @Override
    public Activity findByDescription(String description) {
        Activity activity = activityRepository.findByDescription(description);

        return activity;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC')")
    public long addActivityToWorkOrder(Activity activity) {
        Activity createActivity = activityRepository.save(activity);
        createActivity.setCreated(java.time.LocalDateTime.now());
        createActivity.setModified(java.time.LocalDateTime.now());
        activityRepository.save(createActivity);

        return createActivity.getId();
    }
}
