package nl.akker.springboot.backend.application.model.tables;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Car")
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "license plate must not be empty")
    @Column(name = "license_plate", nullable = false, columnDefinition = "TEXT", length = 11)
    private String licensePlate;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;

    @JsonBackReference(value = "customer-car") // To avoid Infinite recursion (StackOverflowError) when the API GET method is being requested
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "car_customer",
            joinColumns = @JoinColumn(name = "car_id"),
            foreignKey = @ForeignKey(name = "car_id_FK"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"),
            inverseForeignKey = @ForeignKey(name = "customer_id_FK"))
    private Customer customer;

}
