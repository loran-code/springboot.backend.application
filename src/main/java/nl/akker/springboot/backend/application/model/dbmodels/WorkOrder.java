package nl.akker.springboot.backend.application.model.dbmodels;

import lombok.*;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;

import javax.persistence.*;

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

    @Column(name = "work_order_number", nullable = false, updatable = false, unique = true)
    private Long workOrderNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EWorkOrderStatus status;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @Column(name = "invoice_number", updatable = false, unique = true)
    private Long invoiceNumber;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "car_id_FK"))
    private Car car;

    @OneToMany
    @JoinColumn(name = "activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "activity_id_FK"))
    private List<Activity> activities;

    @OneToMany
    @JoinColumn(name = "component_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "component_id_FK"))
    private List<Component> components;

    @OneToMany
    @JoinColumn(name = "additional_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "additional_id_FK"))
    private List<Additional> additionals;

    public WorkOrder(Long workOrderNumber, EWorkOrderStatus status, LocalDateTime appointmentDate, Long invoiceNumber, Car car) {
        this.workOrderNumber = workOrderNumber;
        this.status = status;
        this.appointmentDate = appointmentDate;
        this.invoiceNumber = invoiceNumber;
        this.car = car;
    }
}