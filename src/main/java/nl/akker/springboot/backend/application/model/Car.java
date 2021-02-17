package nl.akker.springboot.backend.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "Employee")
@Table(name = "employee")
public class Car {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    private String licensePlate;

    private LocalDateTime created;

    private LocalDateTime modified;

    @ManyToOne
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
