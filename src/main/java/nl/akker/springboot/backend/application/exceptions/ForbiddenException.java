package nl.akker.springboot.backend.application.exceptions;

public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        super("You are not authorized to access the specified resource.");
    }
}