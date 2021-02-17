package nl.akker.springboot.backend.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "WorkOrder")
@Table(name = "workorder")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "work_order_number", updatable = false)
    private Long workOrderNumber;

    @Column(name = "invoice_number", updatable = false)
    private Long invoiceNumber;

    @Column(name = "inspection_date", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date inspectionDate;

    @Column(name = "customer_has_agreed", columnDefinition = "BOOLEAN")
    private boolean AgreementByCustomer;

    @Column(name = "repair_date", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date repairDate;

    @Enumerated(EnumType.STRING)
    private EWorkOrderStatus status;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
}