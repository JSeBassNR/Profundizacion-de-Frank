package com.example.sheep.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Respuesta est�ndar del servicio de autenticaci�n.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
 private Long idUsuario;
 private String nombreCompleto;
 private String email;
 private String telefono;
 private String rol;
 private Boolean activo;
 private LocalDateTime fechaRegistro;
 private String ubicacion;
}
