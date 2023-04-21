package dev.nukem.emailsystem.exception;

public class EmailTakenException extends RuntimeException {
    public EmailTakenException() {
        super("Email is already taken");
    }
}

