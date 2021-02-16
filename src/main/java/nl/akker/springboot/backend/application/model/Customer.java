package nl.akker.springboot.backend.application.model;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private String zip;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "modified")
    private LocalDateTime modified;

    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, String phone, String email, String street, String city, String zip, LocalDate created, LocalDateTime modified) {
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

    public Customer(String firstName, String lastName, String phone, String email, String street, String city, String zip, LocalDate created, LocalDateTime modified) {
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








