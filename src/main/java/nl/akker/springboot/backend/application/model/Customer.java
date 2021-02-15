package nl.akker.springboot.backend.application.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "Customer")
//@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "customer_sequence")
//    @Column(name = "id", updatable = false)
    private Long id;

//    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

//    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

//    @Column(name = "phone", nullable = false, columnDefinition = "TEXT", unique = true)
    private String phone;

//    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;

//    @Column(name = "street",columnDefinition = "TEXT")
    private String street;

//    @Column(name = "city",columnDefinition = "TEXT")
    private String city;

//    @Column(name = "zip",columnDefinition = "TEXT")
    private String zip;

//    @Column(name = "created",columnDefinition = "DATETIME")
    private LocalDateTime created;

//    @Column(name = "modified",columnDefinition = "DATETIME")
    private LocalDateTime modified;

    public Customer() {}

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


