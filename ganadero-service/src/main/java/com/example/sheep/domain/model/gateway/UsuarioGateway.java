package com.example.sheep.domain.model.gateway;

import com.example.sheep.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioGateway {

    Usuario guardar(Usuario usuario);

    void eliminarPorId(Long id);

    Usuario buscarPorId(Long id);

    Usuario actualizarUsuario(Usuario usuario);

    Usuario buscarPorEmail(String email);

    Page<Usuario> obtenerPaginado(Pageable pageable);
}