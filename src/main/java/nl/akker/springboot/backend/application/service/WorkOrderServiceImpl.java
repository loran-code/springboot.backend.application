package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrderIncurredCosts;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;
import nl.akker.springboot.backend.application.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@AllArgsConstructor
public class WorkOrderServiceImpl implements WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final WorkOrderIncurredCostsRepository workOrderIncurredCostsRepository;


    @Override
    public Collection<WorkOrder> getWorkOrders() {
        return workOrderRepository.findAll();
    }

    @Override
    public WorkOrder getWorkOrderById(Long id) {
        return workOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Work order with id " + id + " not found"));
    }

    @Override
    public WorkOrder getWorkOrderByWorkOrderNumber(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }
        return workOrderRepository.getWorkOrderByWorkOrderNumber(workOrderNumber);
    }

    @Override
    public ReturnObject createWorkOrder(WorkOrder workOrder) {
        if (workOrderRepository.existsByCarLicensePlate(workOrder.getCar().getLicensePlate())) {
            throw new ApiRequestException("A work order for the specified license plate already exist.");
//            todo opslaan van customer bij auto checken
        } else if (!customerRepository.existsByCarsLicensePlate(workOrder.getCar().getLicensePlate())) {
            throw new ApiRequestException("Not possible to make a work order for a car without an owner. Make sure the specified license plate has a customer attached to it.");
        } else {

            ReturnObject returnObject = new ReturnObject();
            Car car = carRepository.findCarByLicensePlate(workOrder.getCar().getLicensePlate());
            WorkOrder createWorkOrder = workOrder;

            if (car.getCreated() == null) {
                car.setCreated(LocalDateTime.now());
                car.setModified(LocalDateTime.now());
            }

            // Get latest work order number from database
            WorkOrder latestWorkOrder = workOrderRepository.findTopByOrderByCreatedDesc();
            // increment work order number
            createWorkOrder.setWorkOrderNumber(latestWorkOrder.getWorkOrderNumber() + 1);

            createWorkOrder.setCar(car);
            createWorkOrder.setStatus(EWorkOrderStatus.APPOINTMENT_FOR_INSPECTION);
            createWorkOrder.setAppointmentDate(workOrder.getAppointmentDate());
            createWorkOrder.setCreated(LocalDateTime.now());
            createWorkOrder.setModified(LocalDateTime.now());
            workOrderRepository.save(createWorkOrder);

            returnObject.setObject(createWorkOrder);
            returnObject.setMessage("Work order has been created");

            return returnObject;
        }
    }

    @Override
    public String carCheckIn(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }

        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);

        updateWorkOrder.setStatus(EWorkOrderStatus.INSPECTION);
        updateWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);

        return "The work order status has been updated to: INSPECTION";
    }


    @Override
    public String customerAgreed(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }

        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);

        updateWorkOrder.setStatus(EWorkOrderStatus.IN_REPAIR); // Customer agreed to repair costs
        updateWorkOrder.setAppointmentDate(updateWorkOrder.getAppointmentDate()); // The date the repair takes place
        updateWorkOrder.setModified(java.time.LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);

        return "Work order status has been updated to: IN_REPAIR";
    }

    @Override
    public String customerDeclined(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }

        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);

        // Customer declined to repair costs and will be invoiced for inspection activity
        updateWorkOrder.setStatus(EWorkOrderStatus.CUSTOMER_DECLINED);
        updateWorkOrder.setModified(java.time.LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);

        //todo trigger invoice method

        return "Work order status has been updated to: CUSTOMER_DECLINED";
    }

//    todo delete?
    @Override
    public ReturnObject updateWorkOrder(WorkOrder workOrder) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrder.getWorkOrderNumber())) {
            throw new NotFoundException("The specified work order number " + workOrder.getWorkOrderNumber() + " has not been found");
        }
        ReturnObject returnObject = new ReturnObject();

        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrder.getWorkOrderNumber());
        WorkOrderIncurredCosts workOrderIncurredCosts = new WorkOrderIncurredCosts();


//        if (workOrderIncurredCosts.getComponents() != null) {
//            for (AddComponent component : updateWorkOrder.rts) {
//                workorderRowService.AddPart(part, workorder);
//            }
//        }
//
//        if (workOrderIncurredCosts.getActivities() != null) {
//            for (AddActivity activity : updateWorkOrder.addLabors) {
//                workorderRowService.AddLabor(labor, workorder);
//            }
//        }

        updateWorkOrder.setModified(java.time.LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);

        returnObject.setObject(updateWorkOrder);
        returnObject.setMessage("Work order has been updated!");

        return returnObject;
    }

    @Override
    public String finishedRepair(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }

        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);

        updateWorkOrder.setStatus(EWorkOrderStatus.INVOICED);
        updateWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);
//            todo generate invoice based on the details in the saved work order

        return "Car repair finished. Work order status has been updated to: INVOICED";
    }


    @Override
    public void deleteWorkOrder(Long id) {
        if (!workOrderRepository.existsById(id)) {
            throw new ApiRequestException("Work order with id " + id + " has not been found");
        }
        workOrderRepository.deleteById(id);
    }
}
