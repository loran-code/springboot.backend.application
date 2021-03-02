package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.dbmodels.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Activity findByActivityNumber(int activityNumber);

    Activity findTopByOrderByActivityNumberDesc();

    boolean existsByActivityNumber(int activityNumber);
}
