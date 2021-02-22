package nl.akker.springboot.backend.application.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("Cannot find specified user.");
    }
}
