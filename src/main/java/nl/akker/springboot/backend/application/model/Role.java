package nl.akker.springboot.backend.application.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private ERole eRole;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "employee_id_role_id_FK"))
    private Employee employee;

    public Role() {
    }

    public Role(Employee employee, ERole eRole) {
        this.employee = employee;
        this.eRole = eRole;
    }

    public Role(Long id, Employee employee, ERole eRole) {
        this.id = id;
        this.employee = employee;
        this.eRole = eRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getRoleName() {
        return eRole;
    }

    public void setRoleName(ERole roleName) {
        this.eRole = roleName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
