package com.example.sheep.domain.exception;

/**
 * Tel�fono inv�lido.
 */
public class InvalidPhoneException extends DomainValidationException {
    private static final long serialVersionUID = 1L;

    public InvalidPhoneException(String message) {
 super(message);
 }
    public InvalidPhoneException(String message, Throwable cause) {
 super(message, cause);
 }
}
