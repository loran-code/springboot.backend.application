package nl.akker.springboot.backend.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "Employee")
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "user_name", nullable = false, columnDefinition = "TEXT")
    private String userName;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "login_tatus", nullable = false, columnDefinition = "TEXT")
    private String loginStatus;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;

    @ManyToMany
    @JoinTable(name = "employee_role",
            foreignKey = @ForeignKey(name = "employee_id_FK"),
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseForeignKey = @ForeignKey(name = "role_id_FK"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

//    @OneToMany(mappedBy = "employee", orphanRemoval = true,
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
////    @JoinColumns(name = "role_id", referencedColumnName = "id")
//    private Set<Role> roles;

    public Employee() {
    }

    public Employee(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Employee(String firstName, String lastName, String userName, String password, String loginStatus, LocalDateTime created, LocalDateTime modified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.loginStatus = loginStatus;
        this.created = created;
        this.modified = modified;
    }

    public Employee(Role role, String firstName, String lastName, String userName, String password, String loginStatus, LocalDateTime created, LocalDateTime modified) {
        this.roles = (List<Role>) role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.loginStatus = loginStatus;
        this.created = created;
        this.modified = modified;
    }

//    public void addRole(Role role) {
//        if (!this.roles.contains(role)) {
//            this.roles.add(role);
//            role.setEmployee((List<Employee>) this);
//        }
//    }
//
//    public void removeRole(Role role) {
//        if (this.roles.contains(role)) {
//            this.roles.remove(role);
//            role.setEmployee(null);
//        }
//    }
}


