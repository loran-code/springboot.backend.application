package nl.akker.springboot.backend.application.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WorkOrder")
@Table(name = "workorder")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "work order number must not be empty")
    @Column(name = "work_order_number", nullable = false, updatable = false)
    private Long workOrderNumber;

    @NotBlank(message = "invoice number must not be empty")
    @Column(name = "invoice_number", updatable = false)
    private Long invoiceNumber;

    @NotBlank(message = "inspection date must not be empty")
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

    @OneToMany
    private List<WorkOrderIncurredCosts> workOrderIncurredCosts;
}