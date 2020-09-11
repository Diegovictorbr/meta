package br.com.meta.exception;

import br.com.meta.dto.Response;

import java.util.Arrays;
import java.util.List;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private List<Response.Message> errors;

    public ResourceNotFoundException(String message) {
        this(null, message);
    }

    public ResourceNotFoundException(String id, String message) {
        this(Arrays.asList(new Response.Message(id, message)));
    }

    public ResourceNotFoundException(List<Response.Message> errors) {
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
