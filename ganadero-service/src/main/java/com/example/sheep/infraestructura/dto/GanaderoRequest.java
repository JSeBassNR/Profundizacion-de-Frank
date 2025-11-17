package com.example.sheep.infraestructura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GanaderoRequest {
 private String nombre;
 private String email;
 private String telefono;
 private String ubicacion;
}
