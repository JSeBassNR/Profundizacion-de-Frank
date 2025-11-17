package com.example.sheep.domain.exception;

public class InvalidNameException extends DomainValidationException {
 public InvalidNameException(String message) {
 super(message);
 }
}
