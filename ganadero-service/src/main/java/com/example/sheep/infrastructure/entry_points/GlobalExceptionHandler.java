package com.example.sheep.infrastructure.entry_points;

import com.example.sheep.domain.exception.DomainValidationException;
import com.example.sheep.domain.exception.GanaderoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(GanaderoNotFoundException.class)
 public ResponseEntity<Map<String, Object>> handleNotFound(GanaderoNotFoundException ex){
 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
 "timestamp", LocalDateTime.now().toString(),
 "error", "Not Found",
 "message", ex.getMessage()
));
 }

 @ExceptionHandler(DomainValidationException.class)
 public ResponseEntity<Map<String, Object>> handleValidation(DomainValidationException ex){
 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
 "timestamp", LocalDateTime.now().toString(),
 "error", "Bad Request",
 "message", ex.getMessage()
));
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<Map<String, Object>> handleOther(Exception ex){
 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
 "timestamp", LocalDateTime.now().toString(),
 "error", "Internal Server Error",
 "message", ex.getMessage()
));
 }
}
