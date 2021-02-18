package nl.akker.springboot.backend.application;

import nl.akker.springboot.backend.application.service.EmployeeServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
