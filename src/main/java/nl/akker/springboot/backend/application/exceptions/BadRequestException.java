package nl.akker.springboot.backend.application.exceptions;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super("Invalid request.");
    }
}