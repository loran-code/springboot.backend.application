package nl.akker.springboot.backend.application.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "workorderincurredcosts")
@Table(name = "WorkOrderIncurredCosts")
public class WorkOrderIncurredCosts {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "workorder_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "workorder_id_FK"))
    private WorkOrder workorder;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EWorkOrderIncurredCosts type;

    @OneToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "activity_id_FK"))
    private Activity activity;

    @OneToOne
    @JoinColumn(name = "component_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "component_id_FK"))
    private Component component;

    @NotBlank(message = "quantity must not be empty")
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;
}