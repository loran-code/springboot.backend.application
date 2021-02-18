package nl.akker.springboot.backend.application.exceptions;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
