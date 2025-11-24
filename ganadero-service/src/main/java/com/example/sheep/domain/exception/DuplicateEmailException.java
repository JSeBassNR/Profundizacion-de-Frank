package com.example.sheep.domain.exception;

/**
 * Email duplicado en registro/actualizaci�n.
 */
public class DuplicateEmailException extends DomainValidationException {
    private static final long serialVersionUID = 1L;
    public DuplicateEmailException(String message) {
 super(message);
 }

 public DuplicateEmailException(String email, Throwable cause) {
        super("Ya existe un usuario con el correo: " + email, cause);
    }
    public static DuplicateEmailException ofEmail(String email){
        return new DuplicateEmailException("Ya existe un usuario con el correo: " + email);
    }
}
