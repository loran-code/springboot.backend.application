package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findTopByOrderByCreatedDesc();

    boolean existsByDescription(String description);

    Inventory findByDescription(String description);

    Inventory findByStockAmount(int amount);

    boolean findByDescriptionAndStockAmount(String description, int amount);
}
