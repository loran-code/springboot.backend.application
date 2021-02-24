package nl.akker.springboot.backend.application.model.dbmodels;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "firstname must not be empty")
    @Size(min = 2, max = 60, message = "Size must between 1 and 60 characters long")
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    String firstname;

    @NotBlank(message = "lastname must not be empty")
    @Size(min = 2, max = 60, message = "Size must between 1 and 60 characters long")
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastname;

    @NotBlank(message = "phone number must not be empty")
    @Size(min = 10, max = 20, message = "Size must between 10 and 20 characters long")
    @Column(name = "phone", nullable = false, columnDefinition = "TEXT")
    private String phone;

    @Size(min = 6, max = 60, message = "Size must between 6 and 60 characters long")
    @NotBlank(message = "email must not be empty")
    @Email(message = "invalid email format")
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Size(max = 60)
    @Column(name = "street", columnDefinition = "TEXT")
    private String street;

    @Size(max = 60)
    @Column(name = "city", columnDefinition = "TEXT")
    private String city;

    @Size(max = 7)
    @Column(name = "zip", columnDefinition = "TEXT")
    private String zip;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "customer",  orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Car> car = new ArrayList<>();
}








