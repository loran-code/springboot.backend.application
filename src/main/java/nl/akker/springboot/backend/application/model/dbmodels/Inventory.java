package nl.akker.springboot.backend.application.model.dbmodels;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "Inventory")
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "component_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "component_id_FK"))
    private Component component;

    @NotNull(message = "component number must not be empty")
    @Column(name = "inventory_number", nullable = false, columnDefinition = "INT")
    private int inventoryNumber;

    @NotNull(message = "stock amount must not be empty")
    @Column(name = "stock_amount", nullable = false, columnDefinition = "INT")
    private int stockAmount;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;
}
