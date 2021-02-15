package nl.akker.springboot.backend.application.model;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Table;
//import javax.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
//@Entity
//@Table(name = "customer")
public class Customer {
    //
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
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


