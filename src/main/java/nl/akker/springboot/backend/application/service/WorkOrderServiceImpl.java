package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
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
        if (carRepository.existsByLicensePlate(workOrder.getCar().getLicensePlate())) {
            throw new ApiRequestException("A work order for the specified license plate already exist.");
        }
        ReturnObject returnObject = new ReturnObject();

        WorkOrder createWorkOrder = workOrder;

        Car car = new Car();
        car.setLicensePlate(workOrder.getCar().getLicensePlate());
        if(car.getCreated() == null) {
            car.setCreated(LocalDateTime.now());
            car.setModified(LocalDateTime.now());
        }

        // Get latest work order number from database
        WorkOrder latestWorkOrder = workOrderRepository.findTopByOrderByCreatedDesc();
        // increment work order number
        createWorkOrder.setWorkOrderNumber(latestWorkOrder.getWorkOrderNumber()+1);

        createWorkOrder.setCar(car);
        createWorkOrder.setStatus(EWorkOrderStatus.APPOINTMENT_FOR_INSPECTION);
        createWorkOrder.setAppointmentDate(workOrder.getAppointmentDate());
        createWorkOrder.setCreated(LocalDateTime.now());
        createWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(createWorkOrder);

        returnObject.setObject(createWorkOrder);
        returnObject.setMessage("Work order has been created!");

        return returnObject;
    }

    @Override
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
    public ReturnObject customerDeclined(WorkOrder workOrder) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrder.getWorkOrderNumber())) {
            throw new NotFoundException("The specified work order number " + workOrder.getWorkOrderNumber() + " has not been found");
        }
        ReturnObject returnObject = new ReturnObject();

        if (workOrder.getWorkOrderNumber() != null) {
            WorkOrder updateWorkOrder = workOrderRepository.findById(workOrder.getId()).orElse(null);
            updateWorkOrder.setStatus(EWorkOrderStatus.CUSTOMER_DECLINED); // Customer declined to repair costs and will be invoiced for inspection activity
            updateWorkOrder.setModified(java.time.LocalDateTime.now());
            workOrderRepository.save(updateWorkOrder);

            //todo trigger invoice method

            returnObject.setObject(updateWorkOrder);
            returnObject.setMessage("Work order status has been updated to: DECLINED");

            return returnObject;
        }
        returnObject.setMessage("Could not find a car with the specified license plate.");

        return returnObject;
    }

    @Override
    public void deleteWorkOrder(Long id) {
        if (!workOrderRepository.existsById(id)) {
            throw new ApiRequestException("Work order with id " + id + " has not been found");
        }
        workOrderRepository.deleteById(id);
    }
}
