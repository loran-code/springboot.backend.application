package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.Additional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalRepository extends JpaRepository<Additional, Long> {
}
