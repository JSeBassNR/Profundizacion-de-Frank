package com.example.sheep.infraestructure.entry_points;

import com.example.sheep.domain.exception.DomainValidationException;
import com.example.sheep.domain.exception.DuplicateEmailException;
import com.example.sheep.domain.exception.GanaderoNotFoundException;
import com.example.sheep.domain.exception.InvalidEmailException;
import com.example.sheep.domain.exception.InvalidNameException;
import com.example.sheep.domain.exception.InvalidPhoneException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

/**
 * Manejador global de excepciones para la API de Ganadero.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
 private ResponseEntity<Map<String,Object>> build(HttpStatus status, String message){
 return ResponseEntity.status(status).body(Map.of(
 "timestamp", Instant.now().toString(),
 "status", status.value(),
 "error", status.getReasonPhrase(),
 "message", message
));
 }

 @ExceptionHandler({InvalidEmailException.class, InvalidNameException.class, InvalidPhoneException.class, DomainValidationException.class})
 public ResponseEntity<Map<String,Object>> handleValidation(RuntimeException ex){
 return build(HttpStatus.BAD_REQUEST, ex.getMessage());
 }

 @ExceptionHandler(DuplicateEmailException.class)
 public ResponseEntity<Map<String,Object>> handleDuplicate(DuplicateEmailException ex){
 return build(HttpStatus.CONFLICT, ex.getMessage());
 }

 @ExceptionHandler(GanaderoNotFoundException.class)
 public ResponseEntity<Map<String,Object>> handleNotFound(GanaderoNotFoundException ex){
 return build(HttpStatus.NOT_FOUND, ex.getMessage());
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<Map<String,Object>> handleGeneric(Exception ex){
 return build(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno");
 }
}

