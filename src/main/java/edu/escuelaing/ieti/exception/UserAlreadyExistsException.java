package edu.escuelaing.ieti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(String id) {
        super(HttpStatus.CONFLICT, "user with ID: " + id + " already exists.");
    }
}