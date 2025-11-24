package com.example.sheep.infraestructure.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AnalisisResponse {
    private Long id;
    private Long usuarioId;
    private String referencia;
    private String resultado;
    private LocalDateTime fecha;
}
