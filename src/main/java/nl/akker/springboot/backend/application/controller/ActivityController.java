package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.dbmodels.Activity;
import nl.akker.springboot.backend.application.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/activity/")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping(path = "all")
    public Collection<Activity> getActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping
    public ResponseEntity<Object> addActivityToWorkOrder(@RequestBody @Valid Activity activity) {
        activityService.addActivityToWorkOrder(activity);
        return ResponseEntity.ok().body("New activity(s): " + activity  + " has been added to the work order");
    }
}
