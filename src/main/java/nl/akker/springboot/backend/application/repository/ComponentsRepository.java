package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentsRepository extends JpaRepository<Component, Long> {

}
