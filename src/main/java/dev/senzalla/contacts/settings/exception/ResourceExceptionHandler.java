package dev.senzalla.contacts.settings.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@AllArgsConstructor
public class ResourceExceptionHandler {
    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Error handle(NotFoundException ex) {
        return new Error(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
    @ExceptionHandler(DuplicateException.class)
    public Error handle(DuplicateException ex) {
        return new Error(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(FailureAuthenticationException.class)
    public Error handle(FailureAuthenticationException ex) {
        return new Error(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtException.class)
    public Error handle(JwtException ex) {
        return new Error(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(DateException.class)
    public Error handle(DateException ex) {
        return new Error(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExternalUser.class)
    public Error handle(ExternalUser ex) {
        return new Error(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailException.class)
    public Error handle(EmailException ex) {
        return new Error(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<Error> exceptionHandler(MethodArgumentNotValidException exception) {
        List<Error> errors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            Error error = new Error(message, fieldError.getField());

            errors.add(error);
        });
        return errors;
    }
}
