package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.tables.WorkOrderIncurredCosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderIncurredCostsRepository extends JpaRepository<WorkOrderIncurredCosts, Long> {

}
