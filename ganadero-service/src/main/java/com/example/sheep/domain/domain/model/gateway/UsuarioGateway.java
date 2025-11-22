package com.example.Sheep.domain.domain.model.gateway;

/**
 * Puerto de salida para consultar el microservicio de Ganadero.
 * Permite validar la existencia de un ganadero por su identificador.
 */
public interface UsuarioGateway {
 boolean existeUsuario(Long id);
}

