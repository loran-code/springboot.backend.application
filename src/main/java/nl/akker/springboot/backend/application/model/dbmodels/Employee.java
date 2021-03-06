package nl.akker.springboot.backend.application.model.dbmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Employee")
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Email
    @NotBlank(message = "email must not be empty")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "username must not be empty")
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "password must not be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @ManyToMany
    @JoinTable(name = "employee_role",
            foreignKey = @ForeignKey(name = "employee_id_FK"),
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseForeignKey = @ForeignKey(name = "role_id_FK"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Employee(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

