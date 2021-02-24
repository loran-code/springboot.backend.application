package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;

import java.util.Collection;

public interface WorkOrderService {

    Collection<WorkOrder> getWorkOrders();

    WorkOrder getWorkOrderById(Long id);

    WorkOrder getWorkOrderByWorkOrderNumber(Long workOrderNumber);

    ReturnObject createWorkOrder(WorkOrder workOrder);

    String carCheckIn(Long woNumber);

    ReturnObject customerAgreed(WorkOrder workOrder);

    ReturnObject customerDeclined(WorkOrder workOrder);

    void deleteWorkOrder(Long id);
}
