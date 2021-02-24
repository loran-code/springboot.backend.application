package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;
import nl.akker.springboot.backend.application.service.WorkOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/workorder/")
public class WorkOrderController {
    
    private final WorkOrderService workOrderService;

    @GetMapping(path = "all")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE, ROLE_BACKOFFICE')")
    public Collection<WorkOrder> getWorkOrders() {
        return workOrderService.getWorkOrders();
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE, ROLE_BACKOFFICE')")
    public WorkOrder getWorkOrder(@PathVariable("id") Long id) {
        return workOrderService.getWorkOrderById(id);
    }

    @GetMapping(path = "workordernumber/{workordernumber}")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE, ROLE_BACKOFFICE')")
    public WorkOrder findWorkOrderByWorkOrderNumber(@PathVariable("workordernumber") Long workOrderNumber){
        return workOrderService.getWorkOrderByWorkOrderNumber(workOrderNumber);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> registerNewWorkOrder(@RequestBody @Valid WorkOrder workOrder) {
        ReturnObject returnObject = workOrderService.createWorkOrder(workOrder);
        return ResponseEntity.ok().body(returnObject);
    }

    @PostMapping("checkin/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_FRONTOFFICE')")
    public ResponseEntity<MessageResponse> CheckInCar(@RequestBody @Valid Long workOrderNumber) {
        String response = workOrderService.carCheckIn(workOrderNumber);
        return ResponseEntity.ok().body(new MessageResponse(response));
    }

    @PatchMapping(value = "agreed/{workordernumber}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> customerAgreedToCarRepair(@PathVariable("workordernumber") @RequestBody @Valid WorkOrder workOrder) {
        ReturnObject returnObject = workOrderService.customerAgreed(workOrder);
        return ResponseEntity.ok().body(returnObject);
    }

    @PostMapping(value = "declined/{workordernumber}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> customerDeclinedCarRepair(@PathVariable("workordernumber") @RequestBody @Valid WorkOrder workOrder) {
        ReturnObject returnObject = workOrderService.customerDeclined(workOrder);
        return ResponseEntity.ok().body(returnObject);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteWorkOrder(@PathVariable("id") Long id) {
        workOrderService.deleteWorkOrder(id);
        return ResponseEntity.ok().body("The work order with id " + id + " has been deleted");
    }
}
