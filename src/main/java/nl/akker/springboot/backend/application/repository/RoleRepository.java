package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.enums.ERole;
import nl.akker.springboot.backend.application.model.dbmodels.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleDescription(ERole roleDescription);
}
