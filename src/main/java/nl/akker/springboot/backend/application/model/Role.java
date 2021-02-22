package nl.akker.springboot.backend.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_description", nullable = false)
    private ERole roleDescription;

}
