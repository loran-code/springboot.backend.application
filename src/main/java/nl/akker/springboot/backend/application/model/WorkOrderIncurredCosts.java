package nl.akker.springboot.backend.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "workorderincurredcosts")
@Table(name = "WorkOrderIncurredCosts")
public class WorkOrderIncurredCosts {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "row_number", nullable = false)
    private int rowNumber;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;

    @OneToOne
    @JoinColumn(name = "workorder_id", referencedColumnName = "id")
    private WorkOrder workorder;

    @ManyToMany
    private List<Component> component;

    @ManyToMany
    private List<Activity> activity;
}