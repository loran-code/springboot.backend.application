package nl.akker.springboot.backend.application.model.dbmodels;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "Activity")
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotNull(message = "activity number must not be empty")
    @Column(name = "activity_number", updatable = false, nullable = false)
    int activityNumber;

    @NotBlank(message = "description must not be empty")
    @Column(name = "description", nullable = false)
    String description;

    @NotNull(message = "price must not be empty")
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;
}