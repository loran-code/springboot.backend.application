package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
