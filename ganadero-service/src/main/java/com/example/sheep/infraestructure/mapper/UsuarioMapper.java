package com.example.sheep.infraestructure.mapper;

import com.example.sheep.domain.model.Usuario;
import com.example.sheep.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.example.sheep.infraestructure.dto.UsuarioRequest;
import com.example.sheep.infraestructure.dto.GanaderoResponse;
import com.example.sheep.infraestructure.dto.GanaderoUpdateRequest;
import org.springframework.stereotype.Component;


@Component
public class UsuarioMapper {
 public Usuario toDomain(UsuarioData data){
 if(data==null) return null;
 Usuario g = new Usuario();
 g.setId(data.getId());
 g.setNombre(data.getNombre());
 g.setEmail(data.getEmail());
 g.setTelefono(data.getTelefono());
 g.setRol(data.getRol());
 g.setPasswordHash(data.getPasswordHash());
 g.setActivo(data.getActivo());
 g.setFechaRegistro(data.getFechaRegistro());
 g.setUbicacion(data.getUbicacion());
 return g;
 }
 public UsuarioData toData(Usuario domain){
 if(domain==null) return null;
 UsuarioData d = new UsuarioData();
 d.setId(domain.getId());
 d.setNombre(domain.getNombre());
 d.setEmail(domain.getEmail());
 d.setTelefono(domain.getTelefono());
 d.setRol(domain.getRol());
 d.setPasswordHash(domain.getPasswordHash());
 d.setActivo(domain.getActivo());
 d.setFechaRegistro(domain.getFechaRegistro());
 d.setUbicacion(domain.getUbicacion());
 return d;
 }

 // DTO -> Domain
 public Usuario toDomain(UsuarioRequest request){
 if(request==null) return null;
 Usuario g = new Usuario();
 // nombreCompleto del DTO se mapea al campo nombre del dominio
 g.setNombre(request.getNombreCompleto());
 g.setEmail(request.getEmail());
 g.setTelefono(request.getTelefono());
 g.setRol(request.getRol());
 g.setPasswordHash(request.getPasswordHash());
 g.setActivo(request.getActivo());
 g.setUbicacion(request.getUbicacion());
 return g;
 }
 public Usuario toDomain(GanaderoUpdateRequest request){
 if(request==null) return null;
 Usuario g = new Usuario();
 g.setId(request.getIdUsuario());
 g.setNombre(request.getNombreCompleto());
 g.setEmail(request.getEmail());
 g.setTelefono(request.getTelefono());
 g.setRol(request.getRol());
 g.setPasswordHash(request.getPasswordHash());
 g.setActivo(request.getActivo());
 g.setUbicacion(request.getUbicacion());
 return g;
 }

 // Domain -> DTO
 public GanaderoResponse toResponse(Usuario domain){
 if(domain==null) return null;
 GanaderoResponse r = new GanaderoResponse();
 r.setIdUsuario(domain.getId());
 r.setNombreCompleto(domain.getNombre());
 r.setEmail(domain.getEmail());
 r.setTelefono(domain.getTelefono());
 r.setRol(domain.getRol());
 r.setActivo(domain.getActivo());
 r.setFechaRegistro(domain.getFechaRegistro());
 r.setUbicacion(domain.getUbicacion());
 return r;
 }
}

