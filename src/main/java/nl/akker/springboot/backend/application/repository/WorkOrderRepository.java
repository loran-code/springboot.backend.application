package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

    WorkOrder getWorkOrderByWorkOrderNumber(Long workOrderNumber);
    Boolean existsByWorkOrderNumber(Long workOrderNumber);
    WorkOrder findTopByOrderByCreatedDesc();
}
