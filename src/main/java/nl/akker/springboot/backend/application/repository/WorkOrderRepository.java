package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

    WorkOrder getWorkOrderByWorkOrderNumber(Long workOrderNumber);

    Boolean existsByWorkOrderNumber(Long workOrderNumber);

    WorkOrder findTopByOrderByCreatedDesc();

    WorkOrder findByWorkOrderNumber(Long workOrderNumber);

    WorkOrder findByInvoiceNumber(Long invoiceNumber);

    WorkOrder findWorkOrderByStatus(EWorkOrderStatus status);

    Boolean existsByCarLicensePlate(String licensePlate);

    List<WorkOrder> findByStatus(EWorkOrderStatus status);
}
