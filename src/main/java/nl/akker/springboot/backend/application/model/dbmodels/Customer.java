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
    @Column(name = "first_name", nullable = false)
    String firstname;

    @NotBlank(message = "lastname must not be empty")
    @Size(min = 2, max = 60, message = "Size must between 1 and 60 characters long")
    @Column(name = "last_name", nullable = false)
    private String lastname;

    @NotBlank(message = "phone number must not be empty")
    @Size(min = 10, max = 20, message = "Size must between 10 and 20 characters long")
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Size(min = 6, max = 60, message = "Size must between 6 and 60 characters long")
    @NotBlank(message = "email must not be empty")
    @Email(message = "invalid email format")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(max = 60)
    @Column(name = "street")
    private String street;

    @Size(max = 60)
    @Column(name = "city")
    private String city;

    @Size(max = 7)
    @Column(name = "zip")
    private String zip;

    @Column(name = "add_car_papers")
    private boolean carPapers;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "customer")
    private List<Car> cars = new ArrayList<>();

    public Customer(@NotBlank(message = "firstname must not be empty") @Size(min = 2, max = 60, message = "Size must between 1 and 60 characters long") String firstname, @NotBlank(message = "lastname must not be empty") @Size(min = 2, max = 60, message = "Size must between 1 and 60 characters long") String lastname, @NotBlank(message = "phone number must not be empty") @Size(min = 10, max = 20, message = "Size must between 10 and 20 characters long") String phone, @Size(min = 6, max = 60, message = "Size must between 6 and 60 characters long") @NotBlank(message = "email must not be empty") @Email(message = "invalid email format") String email, @Size(max = 60) String street, @Size(max = 60) String city, @Size(max = 7) String zip, boolean carPapers, LocalDateTime created, LocalDateTime modified, List<Car> cars) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.carPapers = carPapers;
        this.created = created;
        this.modified = modified;
        this.cars = cars;
    }
}








