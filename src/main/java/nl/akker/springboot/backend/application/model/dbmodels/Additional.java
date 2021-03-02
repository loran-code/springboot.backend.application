package nl.akker.springboot.backend.application.model.dbmodels;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "Additional")
@Table(name = "additional")
public class Additional {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "description")
    String description;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private double price;
}
