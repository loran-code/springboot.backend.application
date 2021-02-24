package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;
import nl.akker.springboot.backend.application.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@AllArgsConstructor
public class WorkOrderServiceImpl implements WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final CarRepository carRepository;
    private final WorkOrderIncurredCostsRepository workOrderIncurredCostsRepository;


    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE', 'ROLE_BACKOFFICE')")
    public Collection<WorkOrder> getWorkOrders() {
        return workOrderRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE', 'ROLE_BACKOFFICE')")
    public WorkOrder getWorkOrderById(Long id) {
        return workOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Work order with id " + id + " not found"));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE', 'ROLE_BACKOFFICE')")
    public WorkOrder getWorkOrderByWorkOrderNumber(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }
        return workOrderRepository.getWorkOrderByWorkOrderNumber(workOrderNumber);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRONTOFFICE')")
    public ReturnObject createWorkOrder(WorkOrder workOrder) {
        ReturnObject returnObject = new ReturnObject();
        Car car = carRepository.findCarByLicensePlate(workOrder.getCar().getLicensePlate());

        if (car != null) {
            WorkOrder createWorkOrder = workOrder;
//            createWorkOrder.setWorkOrderNumber(106L); auto + 1
            createWorkOrder.setCar(car);
            createWorkOrder.setStatus(EWorkOrderStatus.APPOINTMENT_FOR_INSPECTION);
            createWorkOrder.setAppointmentDate(workOrder.getAppointmentDate());
//            createWorkOrder.setInvoiceNumber(1006L); auto + 1
            createWorkOrder.setCreated(LocalDateTime.now());
            createWorkOrder.setModified(LocalDateTime.now());
            workOrderRepository.save(createWorkOrder);

            returnObject.setObject(createWorkOrder);
            returnObject.setMessage("Work order has been created!");

            return returnObject;
        }
        returnObject.setMessage("Could not find a car with the specified license plate.");

        return returnObject;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRONTOFFICE')")
    public String carCheckIn(Long workOrderNumber) {
        WorkOrder workOrder = workOrderRepository.getWorkOrderByWorkOrderNumber(workOrderNumber);

        if (workOrder != null) {
            workOrder.setStatus(EWorkOrderStatus.INSPECTION);
            workOrder.setModified(LocalDateTime.now());

            workOrderRepository.save(workOrder);

            return "The work order status has been updated to: INSPECTION";
        }

        return "Could not find any work orders with the specified number: " + workOrderNumber;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MECHANIC', 'ROLE_FRONTOFFICE')")
    public ReturnObject customerAgreed(WorkOrder workOrder) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrder.getWorkOrderNumber())) {
            throw new NotFoundException("The specified work order number " + workOrder.getWorkOrderNumber() + " has not been found");
        }
        ReturnObject returnObject = new ReturnObject();

        if (workOrder.getWorkOrderNumber() != null) {
            WorkOrder updateWorkOrder = workOrderRepository.findById(workOrder.getId()).orElse(null);
            updateWorkOrder.setStatus(EWorkOrderStatus.IN_REPAIR); // Customer agreed to repair costs
            updateWorkOrder.setAppointmentDate(workOrder.getAppointmentDate()); // The date the repair takes place
            updateWorkOrder.setModified(java.time.LocalDateTime.now());
            workOrderRepository.save(updateWorkOrder);

            returnObject.setObject(updateWorkOrder);
            returnObject.setMessage("Work order status has been updated to: REPAIR");

            return returnObject;
        }
        returnObject.setMessage("Could not find a car with the specified license plate.");

        return returnObject;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteWorkOrder(Long id) {
        if (!workOrderRepository.existsById(id)) {
            throw new ApiRequestException("Work order with id " + id + " has not been found");
        }
        workOrderRepository.deleteById(id);
    }
}
