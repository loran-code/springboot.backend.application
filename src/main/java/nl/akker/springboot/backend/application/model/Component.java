package nl.akker.springboot.backend.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "Component")
@Table(name = "component")
public class Component {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;

    public Component() {
    }

    public Component(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public Component(String description, double price, LocalDateTime created, LocalDateTime modified) {
        this.description = description;
        this.price = price;
        this.created = created;
        this.modified = modified;
    }
}