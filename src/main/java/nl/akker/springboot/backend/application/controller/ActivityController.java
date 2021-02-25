package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Activity;
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

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MECHANIC')")
    public ResponseEntity<Object> addActivityToWorkOrder(@RequestBody @Valid Activity activity) {
        ReturnObject returnObject = activityService.addActivityToWorkOrder(activity);
        return ResponseEntity.ok().body(returnObject);
    }
}
