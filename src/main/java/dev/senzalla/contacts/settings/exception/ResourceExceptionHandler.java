package dev.senzalla.contacts.settings.exception;

import dev.senzalla.contacts.model.error.module.ErrorDto;
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
    public ErrorDto exceptionHandler(NotFoundException exception) {
        String message = messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale());
        return new ErrorDto(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDto> exceptionHandler(MethodArgumentNotValidException exception) {
        List<ErrorDto> errorDtos = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErrorDto errorDto = new ErrorDto(message, fieldError.getField());

            errorDtos.add(errorDto);
        });
        return errorDtos;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateException.class)
    public ErrorDto handle(DuplicateException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FailureAuthenticationException.class)
    public ErrorDto handle(FailureAuthenticationException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExternalUser.class)
    public ErrorDto handle(ExternalUser ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailException.class)
    public ErrorDto handle(EmailException ex) {
        return new ErrorDto(ex.getMessage());
    }
}
