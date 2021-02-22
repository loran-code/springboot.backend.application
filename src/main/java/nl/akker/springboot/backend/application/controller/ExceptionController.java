package nl.akker.springboot.backend.application.controller;

import nl.akker.springboot.backend.application.exceptions.BadRequestException;
import nl.akker.springboot.backend.application.exceptions.ForbiddenException;
import nl.akker.springboot.backend.application.exceptions.RecordNotFoundException;
import nl.akker.springboot.backend.application.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> exception(ForbiddenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
