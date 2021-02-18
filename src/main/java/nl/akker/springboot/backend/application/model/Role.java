package nl.akker.springboot.backend.application.model;

import javax.persistence.*;

import java.util.List;

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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "employee_id",
//            nullable = false,
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(name = "employee_id_role_id_FK"))
//    private Employee employee;

    @ManyToMany(mappedBy = "roles")
    private List<Employee> employee;

    public Role() {
    }

    public Role(ERole eRole) {
        this.eRole = eRole;
    }

    public Role(ERole eRole, List<Employee> employee) {
        this.eRole = eRole;
        this.employee = employee;
    }

    public Role(Long id, ERole eRole, List<Employee> employee) {
        this.id = id;
        this.eRole = eRole;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole geteRole() {
        return eRole;
    }

    public void seteRole(ERole eRole) {
        this.eRole = eRole;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
