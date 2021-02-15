package nl.akker.springboot.backend.application.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1)

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence")

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String zip;
    private LocalDateTime created;
    private LocalDateTime modified;

    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, String phone, String email, String street, String city, String zip, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.created = created;
        this.modified = modified;
    }

    public Customer(String firstName, String lastName, String phone, String email, String street, String city, String zip, LocalDateTime created, LocalDateTime modified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.created = created;
        this.modified = modified;
    }

}


