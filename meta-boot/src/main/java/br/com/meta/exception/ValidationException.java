package br.com.meta.exception;

import br.com.meta.dto.Response;

import java.util.Arrays;
import java.util.List;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private List<Response.Message> errors;

    public ValidationException(String message) {
        this(null, message);
    }

    public ValidationException(String id, String message) {
        this(Arrays.asList(new Response.Message(id, message)));
    }

    public ValidationException(List<Response.Message> errors) {
        super(String.format("Erro de validação: %s", errors));
        this.errors = errors;
    }

    public List<Response.Message> getErrors() {
        return this.errors;
    }

    public void setErrors(List<Response.Message> errors) {
        this.errors = errors;
    }
}
