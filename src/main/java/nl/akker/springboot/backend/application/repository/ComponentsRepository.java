package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentsRepository extends JpaRepository<Component, Long> {

    Component findByComponentNumber(int componentNumber);

    boolean existsByComponentNumber(int componentNumber);

    Component findByDescription(String description);

    boolean existsByDescription(String description);

    Component findTopByOrderByComponentNumberDesc();
}
