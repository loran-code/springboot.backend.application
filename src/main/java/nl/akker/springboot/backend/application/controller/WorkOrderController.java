package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;
import nl.akker.springboot.backend.application.service.WorkOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/workorder/")
public class WorkOrderController {
    
    private final WorkOrderService workOrderService;

    @GetMapping(path = "all")
    public Collection<WorkOrder> getWorkOrders() {
        return workOrderService.getWorkOrders();
    }

    @GetMapping(path = "{id}")
    public WorkOrder getWorkOrder(@PathVariable("id") Long id) {
        return workOrderService.getWorkOrderById(id);
    }

    @GetMapping(path = "workordernumber/{workordernumber}")
    public WorkOrder findWorkOrderByWorkOrderNumber(@PathVariable("workordernumber") Long workOrderNumber){
        return workOrderService.getWorkOrderByWorkOrderNumber(workOrderNumber);
    }

    @PostMapping
    public ResponseEntity<Object> registerNewWorkOrder(@RequestBody @Valid WorkOrder workOrder) {
        workOrderService.createWorkOrder(workOrder);
        return ResponseEntity.ok().body("New work order has been created: " + workOrder);
    }

    @PostMapping("checkin/")
    public ResponseEntity<MessageResponse> CheckInCar(@RequestBody @Valid Long workOrderNumber) {
        String response = workOrderService.carCheckIn(workOrderNumber);
        return ResponseEntity.ok().body(new MessageResponse(response));
    }

    @PatchMapping(value = "agreed/{workordernumber}")
    public ResponseEntity<Object> customerAgreedToCarRepair(@PathVariable("workordernumber") @RequestBody @Valid WorkOrder workOrder) {
        workOrderService.customerAgreed(workOrder);
        return ResponseEntity.ok().body("Work order has been updated: " + workOrder);
    }

//    @PatchMapping(value = "agreed/{workordernumber}")
//    public ResponseEntity<MessageResponse> customerDeclinedCarRepair(@PathVariable("workordernumber") @RequestBody @Valid WorkOrder workOrder) {
//        String response = workOrderService.customerAgreed(workOrderNumber,workOrder);
//        return ResponseEntity.ok().body(new MessageResponse(response));
//    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteWorkOrder(@PathVariable("id") Long id) {
        workOrderService.deleteWorkOrder(id);
        return ResponseEntity.ok().body("The work order with id " + id + " has been deleted");
    }
}
