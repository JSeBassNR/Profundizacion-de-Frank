package com.example.sheep.infraestructure.driver_adapters.jpa_repository;

import com.example.sheep.domain.model.Usuario;
import com.example.sheep.domain.model.gateway.UsuarioGateway;
import com.example.sheep.infraestructure.mapper.MapperUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
@RequiredArgsConstructor
public class UsuarioDataGatewayImpl implements UsuarioGateway {

    private final UsuarioDataJpaRepository repository;
    private final MapperUsuario mapperUsuario;

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioData usuarioData = mapperUsuario.toData(usuario);
        return mapperUsuario.toUsuario(repository.save(usuarioData));
    }

    @Override
    public void eliminarPorId(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception error) {
            throw new RuntimeException(error.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(Long id) {
//        UsuarioData usuarioData = repository.findById(id).get();
//        return mapperUsuario.toUsuario(usuarioData);
        return repository.findById(id)
                .map(usuarioData -> mapperUsuario.toUsuario(usuarioData))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        if(!repository.existsById(usuario.getId())){
            throw new RuntimeException("Usuario con id " + usuario.getId() + " no existe");
        }

        // Cargar entidad existente y actualizar solo campos permitidos, preservando
        // fechaRegistro y password si no se proveen en la solicitud.
        UsuarioData existing = repository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuario con id " + usuario.getId() + " no existe"));

        if(usuario.getNombre() != null) existing.setNombre(usuario.getNombre());
        if(usuario.getEmail() != null) existing.setEmail(usuario.getEmail());
        // Si el DTO trae password (ya cifrada por el usecase), reemplazar; si es null, mantener la existente.
        if(usuario.getPassword() != null && !usuario.getPassword().isBlank()) existing.setPassword(usuario.getPassword());
        if(usuario.getRol() != null) existing.setRol(usuario.getRol());
        if(usuario.getEdad() != null) existing.setEdad(usuario.getEdad());
        if(usuario.getNumeroTelefono() != null) existing.setNumeroTelefono(usuario.getNumeroTelefono());
        if(usuario.getActivo() != null) existing.setActivo(usuario.getActivo());
        if(usuario.getUbicacion() != null) existing.setUbicacion(usuario.getUbicacion());

        // fechaRegistro: mantener la que ya existe (no sobrescribir con null)
        if (existing.getFechaRegistro() == null) {
            existing.setFechaRegistro(LocalDateTime.now());
        }

        return mapperUsuario.toUsuario(repository.save(existing));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(usuarioData -> mapperUsuario.toUsuario(usuarioData))
                .orElseThrow(() -> new RuntimeException("Fallo consulta de base de datos"));
    }

    @Override
    public Page<Usuario> obtenerPaginado(Pageable pageable) {
        return repository.findAll(pageable).map(usuarioData -> mapperUsuario.toUsuario(usuarioData));
    }
}

