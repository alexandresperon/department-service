package br.com.itau.controller;

import br.com.itau.controller.response.Response;
import br.com.itau.enumerator.Status;
import br.com.itau.exception.NotFoundException;
import br.com.itau.exception.NotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@Validated
@RestControllerAdvice
public class ControllerAdvice {

    private final MessageSource messageSource;

    public ControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Response<Void> handleNotFoundException(final NotFoundException e, final Locale locale) {
        log.debug("Request not found", e);
        return this.createResponse(e, locale);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidException.class)
    public Response<Void> handleNotValidException(final NotValidException e, final Locale locale) {
        log.debug("Invalid request", e);
        return this.createResponse(e, locale);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.debug("Invalid request", e);
        return this.createResponse(e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage).toArray(String[]::new));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response<Void> handleDefaultException(final Exception e, final Locale locale) {
        log.error("Failed to process the request", e);
        return this.createResponse(this.messageSource.getMessage("default.error", new Object[]{}, locale));
    }

    private Response<Void> createResponse(final Exception e, final Locale locale) {
        return this.createResponse(this.messageSource.getMessage(e.getMessage(), new Object[]{}, locale));
    }

    private Response<Void> createResponse(final String... message) {
        return new Response<>(Status.ERROR, message);
    }

}
