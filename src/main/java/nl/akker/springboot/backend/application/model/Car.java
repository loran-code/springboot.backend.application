package nl.akker.springboot.backend.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "Car")
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "license_plate", nullable = false, columnDefinition = "TEXT", length = 9)
    private String licensePlate;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_id_car_id_FK"))
    private Customer customer;

    public Car() {
    }

    public Car(String licensePlate, LocalDateTime created, LocalDateTime modified, Customer customer) {
        this.licensePlate = licensePlate;
        this.created = created;
        this.modified = modified;
        this.customer = customer;
    }
}
