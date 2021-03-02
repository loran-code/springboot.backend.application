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

//    ReturnObject addAdditionalLabor(Additional additional);

    String customerAgreed(Long workOrderNumber);

    String customerDeclined(Long workOrderNumber);

    ReturnObject finishedRepair(WorkOrder workOrder);

    ReturnObject payInvoice(WorkOrder workOrder);

    void deleteWorkOrder(Long id);
}
