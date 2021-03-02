package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Activity;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/activity/")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping(path = "all")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN, ROLE_MECHANIC, ROLE_BACKOFFICE')")
    public Collection<Activity> getActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping(path = "create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_BACKOFFICE')")
    public ResponseEntity<Object> createActivity(@Valid @RequestBody Activity activity) {
        ReturnObject returnObject = activityService.createActivity(activity);
        return ResponseEntity.ok().body(returnObject);
    }

    @PostMapping(path = "add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC')")
    public ResponseEntity<Object> addActivityToWorkOrder(@Valid @RequestBody WorkOrder workOrder) {
        ReturnObject returnObject = activityService.addActivityToWorkOrder(workOrder);
        return ResponseEntity.ok().body(returnObject);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_BACKOFFICE')")
    public ResponseEntity<Object> deleteActivity(@PathVariable("id") Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.ok().body("The activity with id " + id + " has been deleted");
    }
}
