package com.postech.hackaton.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(HttpStatus httpStatus, String message) { super(httpStatus, message); }
    public UserAlreadyExistsException(HttpStatus httpStatus, String message, Throwable throwable) { super(httpStatus, message, throwable); }
}