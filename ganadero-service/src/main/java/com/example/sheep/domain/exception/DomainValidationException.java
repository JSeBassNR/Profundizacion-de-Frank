package com.example.sheep.domain.exception;

/**
 * Excepci�n base de validaci�n de dominio.
 */
public class DomainValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DomainValidationException(String message) {
 super(message);
 }
    public DomainValidationException(String message, Throwable cause) {
 super(message, cause);
 }
}
