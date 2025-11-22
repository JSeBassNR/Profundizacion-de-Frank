package com.example.sheep.infraestructure.driver_adapters.jpa_repository;

import com.example.sheep.domain.exception.GanaderoNotFoundException;
import com.example.sheep.domain.model.Ganadero;
import com.example.sheep.domain.model.gateway.GanaderoGateway;
import com.example.sheep.infraestructure.mapper.GanaderoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Adaptador de salida para persistencia JPA de Ganadero.
 * Implementa el puerto de dominio {@link com.example.sheep.domain.model.gateway.GanaderoGateway}.
 */
@Repository
@RequiredArgsConstructor
public class GanaderoDataGatewayImpl implements GanaderoGateway {
 private final GanaderoDataJpaRepository repository;
 private final GanaderoMapper mapper;

 @Override
 public Ganadero guardar(Ganadero ganadero){
 var data = mapper.toData(ganadero);
 var saved = repository.save(data);
 return mapper.toDomain(saved);
 }
 @Override
 public Ganadero buscarPorId(Long id){
 return repository.findById(id).map(mapper::toDomain)
 .orElseThrow(() -> new GanaderoNotFoundException(id));
 }
 @Override
 public Ganadero actualizar(Ganadero ganadero){
 if(!repository.existsById(ganadero.getId())){
 throw new GanaderoNotFoundException(ganadero.getId());
 }
 var data = mapper.toData(ganadero);
 var updated = repository.save(data);
 return mapper.toDomain(updated);
 }
 @Override
 public void eliminarPorIdGanadero(Long id){
 if(!repository.existsById(id)){
 throw new GanaderoNotFoundException(id);
 }
 repository.deleteById(id);
 }
 @Override
 public Ganadero buscarPorEmail(String email){
 var data = repository.findByEmail(email);
 return data==null? null: mapper.toDomain(data);
 }
 @Override
 public Page<Ganadero> obtenerPaginado(Pageable pageable){
 return repository.findAll(pageable).map(mapper::toDomain);
 }
}

