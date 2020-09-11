package br.com.meta.infra;

import br.com.meta.dto.Response;
import br.com.meta.exception.ResourceNotFoundException;
import br.com.meta.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Response<Object>> handleValidation(ValidationException ex, WebRequest request) {
        return Response.status(HttpStatus.BAD_REQUEST.value(), Object.class)
                .messages(ex.getErrors())
                .build();
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Response<Object>> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        return Response.status(HttpStatus.NOT_FOUND.value(), Object.class)
                .messages(ex.getErrors())
                .build();
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Response<Object>> handleInvalidArea(IllegalArgumentException ex, WebRequest request) {
        return Response.status(HttpStatus.BAD_REQUEST.value(), Object.class)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Response<Object>> handleInvalidArea(Exception ex, WebRequest request) {
        final ResponseEntity<Response<Object>> response = Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value(), Object.class)
                .message("Houve um erro interno no processamento da requisição.")
                .exception(ex)
                .build();

        return response;
    }
}
