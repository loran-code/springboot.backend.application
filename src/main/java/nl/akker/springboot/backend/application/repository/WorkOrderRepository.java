package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
}
