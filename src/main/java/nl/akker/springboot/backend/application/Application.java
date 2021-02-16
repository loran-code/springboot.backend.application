package nl.akker.springboot.backend.application;

import nl.akker.springboot.backend.application.model.Customer;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
//        return args -> {
//            Customer loran = new Customer(
//                    "Loran",
//                    "Akker",
//                    "12345678",
//                    "loran@mail.com",
//                    "sesamstraat",
//                    "Den Haag",
//                    "2574AV",
//                    LocalDateTime.now(),
//                    LocalDateTime.now()
//            );
//            customerRepository.save(loran);
//
//            Customer maria = new Customer(
//                    "Maria",
//                    "James",
//                    "12345278",
//                    "maria@mail.com",
//                    "sesamstraat",
//                    "Den Haag",
//                    "2534AV",
//                    LocalDateTime.now(),
//                    LocalDateTime.now()
//            );
//            customerRepository.save(maria);
//        };

}
