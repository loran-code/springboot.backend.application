package nl.akker.springboot.backend.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "car_id_FK"))
    private Car car;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EWorkOrderStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy hh:mm")
    @NotBlank(message = "inspection date must not be empty")
    @Column(name = "appointment_date", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime appointment;

    @NotBlank(message = "invoice number must not be empty")
    @Column(name = "invoice_number", updatable = false)
    private Long invoiceNumber;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;

}