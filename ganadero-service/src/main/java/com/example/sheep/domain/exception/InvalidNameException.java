package com.example.sheep.domain.exception;

/**
 * Nombre o rol invalido.
 */
public class InvalidNameException extends DomainValidationException {
 private static final long serialVersionUID = 1L;

 public InvalidNameException(String message) {
 super(message);
 }
 public InvalidNameException(String message, Throwable cause) {
 super(message, cause);
 }
}
