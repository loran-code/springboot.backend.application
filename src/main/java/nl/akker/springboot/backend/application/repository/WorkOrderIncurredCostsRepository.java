package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.WorkOrderIncurredCosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderIncurredCostsRepository extends JpaRepository<WorkOrderIncurredCosts, Long> {

}
