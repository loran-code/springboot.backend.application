package nl.akker.springboot.backend.application.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

//    TODO "Does this give information to a possible malicious attacker?"
    public UserNotFoundException() {
        super("Cannot find the specified user");
    }
}
