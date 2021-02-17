package nl.akker.springboot.backend.application.model;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity(name = "Employee")
@Table(name = "employee")
public class Employee {

    @Id
//    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToMany(mappedBy = "employee")
//    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private List<Role> role;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "login_tatus", nullable = false)
    private String loginStatus;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "modified")
    private LocalDateTime modified;

    public Employee() {
    }

    public Employee(Role role, String firstName, String lastName, String userName, String password, String loginStatus, LocalDate created, LocalDateTime modified) {
        this.role = (List<Role>) role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.loginStatus = loginStatus;
        this.created = created;
        this.modified = modified;
    }

    public Employee(Long id, Role role, String firstName, String lastName, String userName, String password, String loginStatus, LocalDate created, LocalDateTime modified) {
        this.id = id;
        this.role = (List<Role>) role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.loginStatus = loginStatus;
        this.created = created;
        this.modified = modified;
    }
}


