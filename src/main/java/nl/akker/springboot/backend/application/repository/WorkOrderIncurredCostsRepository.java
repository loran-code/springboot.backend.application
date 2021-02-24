package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrderIncurredCosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderIncurredCostsRepository extends JpaRepository<WorkOrderIncurredCosts, Long> {

    WorkOrderIncurredCosts findWorkOrderIncurredCostsByWorkorderAndDescription(WorkOrder workorder, String description);

    List<WorkOrderIncurredCosts> findWorkOrderIncurredCostsByWorkorder(WorkOrder workorder);

}
