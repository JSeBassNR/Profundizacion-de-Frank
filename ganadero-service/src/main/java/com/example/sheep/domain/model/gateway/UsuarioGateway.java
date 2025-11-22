package com.example.sheep.domain.model.gateway;

import com.example.sheep.domain.model.Usuario;

public interface UsuarioGateway {

    Usuario guardar(Usuario usuario);

    void eliminarPorId(Long id);

    Usuario buscarPorId(Long id);

    Usuario actualizarUsuario(Usuario usuario);

    Usuario buscarPorEmail(String email);
}
