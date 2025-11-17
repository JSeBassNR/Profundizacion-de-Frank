package com.example.sheep.domain.exception;

public class DuplicateEmailException extends DomainValidationException {
 public DuplicateEmailException(String message) {
 super(message);
 }
}
