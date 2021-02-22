package nl.akker.springboot.backend.application.exceptions;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException() {
        super("Cannot find the specified record.");
    }
}