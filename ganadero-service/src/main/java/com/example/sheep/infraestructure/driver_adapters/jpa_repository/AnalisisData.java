package com.example.sheep.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalisisData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long usuarioId;
    private String referencia;
    private String resultado;
    private LocalDateTime fecha;
}
