package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import static org.hibernate.loader.Loader.SELECT;


public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

    WorkOrder getWorkOrderByWorkOrderNumber(Long workOrderNumber);

    Boolean existsByWorkOrderNumber(Long workOrderNumber);

    WorkOrder findTopByOrderByCreatedDesc();

    WorkOrder findByWorkOrderNumber(Long workOrderNumber);

    Boolean existsByCarLicensePlate(String licensePlate);
}
