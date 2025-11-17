package com.example.sheep.domain.exception;

public class InvalidPhoneException extends DomainValidationException {
 public InvalidPhoneException(String message) {
 super(message);
 }
}
