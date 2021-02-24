package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.repository.ActivityRepository;
import nl.akker.springboot.backend.application.repository.ComponentsRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkOrderIncurredCostsImpl implements WorkOrderIncurredCostsService{

    private final ActivityRepository activityRepository;
    private final ComponentsRepository componentsRepository;

}
