package com.example.sheep.domain.exception;

/**
 * Correo invalido.
 */
public class InvalidEmailException extends DomainValidationException {
 private static final long serialVersionUID = 1L;

 public InvalidEmailException(String message) {
 super(message);
 }
 public InvalidEmailException(String message, Throwable cause) {
 super(message, cause);
 }
}
