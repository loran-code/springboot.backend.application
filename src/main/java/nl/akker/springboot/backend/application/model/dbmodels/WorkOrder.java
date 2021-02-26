package nl.akker.springboot.backend.application.model.dbmodels;


import lombok.*;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkOrder")
@Table(name = "workorder")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "work_order_number", nullable = false, updatable = false)
    private Long workOrderNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "car_id_FK"))
    private Car car;

    @OneToMany
    @JoinColumn(name = "activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "activity_id_FK"))
    private List<Activity> activities;

    @OneToMany
    @JoinColumn(name = "component_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "component_id_FK"))
    private List<Component> components;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EWorkOrderStatus status;

    @NotNull(message = "appointment date must not be empty")
    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @Column(name = "invoice_number", updatable = false)
    private Long invoiceNumber;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;
}