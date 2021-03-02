package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;
import nl.akker.springboot.backend.application.service.AdditionalService;
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
    private final AdditionalService additionalService;

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
    public WorkOrder findWorkOrderByWorkOrderNumber(@PathVariable("workordernumber") Long workOrderNumber) {
        return workOrderService.getWorkOrderByWorkOrderNumber(workOrderNumber);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> registerNewWorkOrder(@Valid @RequestBody WorkOrder workOrder) {
        ReturnObject response = workOrderService.createWorkOrder(workOrder);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("checkin/{workOrderNumber}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_FRONTOFFICE')")
    public ResponseEntity<MessageResponse> CheckInCar(@PathVariable("workOrderNumber") Long workOrderNumber) {
        String response = workOrderService.carCheckIn(workOrderNumber);
        return ResponseEntity.ok().body(new MessageResponse(response));
    }

    @PostMapping(value = "agreed/{workordernumber}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE')")
    public ResponseEntity<MessageResponse> customerAgreedToCarRepair(@PathVariable("workordernumber") Long workOrderNumber) {
        String response = workOrderService.customerAgreed(workOrderNumber);
        return ResponseEntity.ok().body(new MessageResponse(response));
    }

    @PostMapping(value = "declined/{workordernumber}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE')")
    public ResponseEntity<MessageResponse> customerDeclinedCarRepair(@PathVariable("workordernumber") Long workOrderNumber) {
        String response = workOrderService.customerDeclined(workOrderNumber);
        return ResponseEntity.ok().body(new MessageResponse(response));
    }

    @PostMapping(value = "additional")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> addAdditionalLabor(@RequestBody WorkOrder workOrder) {
        ReturnObject response = additionalService.addAdditionalLabor(workOrder);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "invoice")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> finishedRepair(@RequestBody WorkOrder workOrder) {
        ReturnObject response = workOrderService.finishedRepair(workOrder);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "pay")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MECHANIC, ROLE_FRONTOFFICE')")
    public ResponseEntity<Object> payInvoice(@RequestBody WorkOrder workOrder) {
        ReturnObject response = workOrderService.payInvoice(workOrder);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteWorkOrder(@PathVariable("id") Long id) {
        workOrderService.deleteWorkOrder(id);
        return ResponseEntity.ok().body("The work order with id " + id + " has been deleted");
    }
}
