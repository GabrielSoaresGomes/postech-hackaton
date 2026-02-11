package com.postech.hackaton.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidEmailException extends ResponseStatusException {
    public InvalidEmailException(HttpStatus httpStatus, String message) { super(httpStatus, message); }
    public InvalidEmailException(HttpStatus httpStatus, String message, Throwable throwable) { super(httpStatus, message, throwable); }
}