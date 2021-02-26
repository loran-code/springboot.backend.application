package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.dbmodels.Activity;
import nl.akker.springboot.backend.application.model.dbmodels.Component;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrderIncurredCosts;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderIncurredCosts;
import nl.akker.springboot.backend.application.repository.ActivityRepository;
import nl.akker.springboot.backend.application.repository.ComponentsRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderIncurredCostsRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class WorkOrderIncurredCostsServiceImpl implements WorkOrderIncurredCostsService {

    private final WorkOrderIncurredCostsRepository workOrderIncurredCostsRepository;
    private final ActivityRepository activityRepository;
    private final ComponentsRepository componentsRepository;
    private final WorkOrderRepository workOrderRepository;


    @Override
    public void AddActivity(Activity activity, WorkOrder workorder) {
        
//        WorkOrderIncurredCosts activity = new WorkOrderIncurredCosts();
//
//        activity.setWorkorder(workorder);
//        activity.setAmount(activity.getAmount());
//        activity.setCustomerAgreed(false);
//        activity.setCreated(LocalDateTime.now());
//        activity.setModified(LocalDateTime.now());
//        activity.setDescription(activityService.getRow(activity.getActivityNumber()).getDescription());
//        activity.setPrice(activityService.getRow(activity.getActivityNumber()).getPrice());
//        activity.setType(EWorkOrderIncurredCosts.ACTIVITY);
//
//        workOrderIncurredCostsRepository.save(activity);
    }

    @Override
    public void AddComponent(Component component, WorkOrder workorder) {

//        WorkOrderIncurredCosts component = new WorkOrderIncurredCosts();
//
//        part.setWorkorder(workorder);
//        part.setAmount(part.getAmount());
//        part.setCustomerAgreed(false);
//        part.setCreated(LocalDateTime.now());
//        part.setModified(LocalDateTime.now());
//        part.setDescription(partService.getRow(part.getPartNumber()).getDescription());
//        part.setPrice(partService.getRow(part.getPartNumber()).getPrice());
//        part.setType(EWorkOrderIncurredCosts.COMPONENT);
//
//        workOrderIncurredCostsRepository.save(part);
    }

    @Override
    public void deleteCost(Long id) {

    }
}
