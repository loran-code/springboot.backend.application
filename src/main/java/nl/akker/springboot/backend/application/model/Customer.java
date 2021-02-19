package nl.akker.springboot.backend.application.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "firstname must not be empty")
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT", length = 25)
    String firstName;

    @NotBlank(message = "lastname must not be empty")
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT", length = 30)
    private String lastName;

    @NotBlank(message = "phone number must not be empty")
    @Column(name = "phone", nullable = false, columnDefinition = "TEXT", length = 20)
    private String phone;

    @NotBlank(message = "email must not be empty")
    @Email(message = "invalid email format")
    @Column(name = "email", nullable = false, columnDefinition = "TEXT", length = 75)
    private String email;

    @Column(name = "street", columnDefinition = "TEXT", length = 50)
    private String street;

    @Column(name = "city", columnDefinition = "TEXT", length = 50)
    private String city;

    @Column(name = "zip", columnDefinition = "TEXT", length = 7)
    private String zip;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "customer", orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Car> car = new ArrayList<>();


    public Customer(Long id, String firstName, String lastName, String phone, String email, String street, String city, String zip) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}








