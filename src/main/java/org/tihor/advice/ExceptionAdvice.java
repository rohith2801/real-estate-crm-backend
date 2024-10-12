package org.tihor.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tihor.exception.InvalidRequestException;
import org.tihor.exception.ResourceNotFoundException;
import org.tihor.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Exception advice.
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    /**
     * Handle method argument not valid exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        log.error("Method Argument Not Valid Exception.", exception);

        var objectErrors = exception.getAllErrors();
        var errors = objectErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toCollection(() -> new ArrayList<>(objectErrors.size())));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.withErrors(errors));
    }

    /**
     * Handle invalid request exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ResponseBody
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleInvalidRequestException(final InvalidRequestException exception) {
        log.error("Invalid request Exception.", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.withErrors(List.of(exception.getMessage())));
    }

    /**
     * Handle resource not found exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleResourceNotFoundException(final ResourceNotFoundException exception) {
        log.error("Resource Not Found Exception.", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.withErrors(List.of(exception.getMessage())));
    }

    /**
     * Handle exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<Response> handleException(final Exception exception) {
        log.error("Exception occurred while processing.", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.withErrors(List.of(exception.getMessage())));
    }
}
