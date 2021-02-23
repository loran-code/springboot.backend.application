package nl.akker.springboot.backend.application.model.tables;

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
@Entity(name = "Activity")
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "activity number must not be empty")
    @Column(name = "activity_number", nullable = false, columnDefinition = "INT")
    String activityNumber;

    @NotBlank(message = "description must not be empty")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    String description;

    @NotBlank(message = "price must not be empty")
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;

}