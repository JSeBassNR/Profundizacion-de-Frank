package com.example.sheep.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Column(length = 30, nullable = false)
    private String email;
    private String password;
    private String rol;
    private Integer edad;
    private String numeroTelefono;
    private Boolean activo;
    private LocalDateTime fechaRegistro;
    private String ubicacion;

    @PrePersist

    private void onCreate(){

        if (this.fechaRegistro == null) {

            this.fechaRegistro = LocalDateTime.now();

        }

        if (this.activo == null) {

            this.activo = true;

        }

    }
}

