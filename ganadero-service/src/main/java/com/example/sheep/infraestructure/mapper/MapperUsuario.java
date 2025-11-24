package com.example.sheep.infraestructure.mapper;

import com.example.sheep.domain.model.Usuario;
import com.example.sheep.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import org.springframework.stereotype.Component;
import com.example.sheep.infraestructure.dto.UsuarioResponse;
import java.time.LocalDateTime;

@Component
public class MapperUsuario {

    public Usuario toUsuario(UsuarioData usuarioData){
        return new Usuario(
                usuarioData.getId(),
                usuarioData.getNombre(),
                usuarioData.getEmail(),
                usuarioData.getPassword(),
                usuarioData.getRol(),
                usuarioData.getEdad(),
                usuarioData.getNumeroTelefono(),
                usuarioData.getActivo(),
                usuarioData.getFechaRegistro(),
                usuarioData.getUbicacion()
        );

    }

    public UsuarioResponse toResponse(Usuario usuario){
        if(usuario == null) return null;
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getNumeroTelefono(),
                usuario.getRol(),
                usuario.getActivo(),
                usuario.getFechaRegistro() == null ? LocalDateTime.now() : usuario.getFechaRegistro(),
                usuario.getUbicacion()
        );
    }

    public UsuarioData toData(Usuario usuario){
        return new UsuarioData(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRol(),
                usuario.getEdad(),
                usuario.getNumeroTelefono(),
                usuario.getActivo(),
                usuario.getFechaRegistro(),
                usuario.getUbicacion()
        );
    }
}
