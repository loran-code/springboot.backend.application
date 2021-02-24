package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;

import java.util.Collection;

public interface WorkOrderService {

    Collection<WorkOrder> getWorkOrders();

    WorkOrder getWorkOrderById(Long id);

    WorkOrder getWorkOrderByWorkOrderNumber(Long workOrderNumber);

    ReturnObject createWorkOrder(WorkOrder workOrder);

    //    long updateWorkOrder(Long id, WorkOrder workOrder);
    ReturnObject customerAgreed(WorkOrder workOrder);

    void deleteWorkOrder(Long id);

    String carCheckIn(Long woNumber);
}
