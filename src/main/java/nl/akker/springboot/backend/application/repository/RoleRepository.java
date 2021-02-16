package nl.akker.springboot.backend.application.repository;

import nl.akker.springboot.backend.application.model.ERole;
import nl.akker.springboot.backend.application.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
