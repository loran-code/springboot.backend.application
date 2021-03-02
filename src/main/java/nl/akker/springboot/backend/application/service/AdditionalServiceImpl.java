package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.dbmodels.Additional;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.repository.AdditionalRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdditionalServiceImpl implements AdditionalService {

    private final AdditionalRepository additionalRepository;
    private final WorkOrderRepository workOrderRepository;

@Override
    public ReturnObject addAdditionalLabor(WorkOrder workOrder) {
        ReturnObject returnObject = new ReturnObject();

        if (workOrder != null) {

            WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrder.getWorkOrderNumber());
            List<Additional> additionals = new ArrayList<>();

            for (Additional updateAdditional : workOrder.getAdditionals()) {
                if (updateAdditional != null) {
                    additionalRepository.save(updateAdditional);
                    additionals.add(updateAdditional);
                }
            }

            updateWorkOrder.setAdditionals(additionals);
            updateWorkOrder.setModified(LocalDateTime.now());
            workOrderRepository.save(updateWorkOrder);

            returnObject.setObject(updateWorkOrder);
            returnObject.setMessage("Additional labor has been added to the work order");

            return returnObject;
        }
        returnObject.setMessage("Additional labor can not be empty. Fill the work order number, additional description," +
                " price and amount");

        return returnObject;
    }
}
