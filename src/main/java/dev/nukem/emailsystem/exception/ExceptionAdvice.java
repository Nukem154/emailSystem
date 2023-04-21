package dev.nukem.emailsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleUserNotFoundException(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(EmailTakenException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleEmailTakenException(EmailTakenException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UsernameTakenException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleUsernameTakenException(UsernameTakenException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(err -> String.format("%s %s", err.getField(), err.getDefaultMessage()))
                .collect(Collectors.joining(", "));
    }

    @ExceptionHandler(CronJobNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleCronJobNotFoundException(CronJobNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
