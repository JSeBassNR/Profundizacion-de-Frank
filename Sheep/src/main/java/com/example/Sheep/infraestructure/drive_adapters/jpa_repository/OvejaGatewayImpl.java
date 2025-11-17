package com.example.Sheep.infraestructure.drive_adapters.jpa_repository;

import com.example.Sheep.domain.exception.DomainValidationException;
import com.example.Sheep.domain.exception.OvejaNotFoundException;
import com.example.Sheep.domain.model.Oveja;
import com.example.Sheep.domain.model.gateway.OvejaGateway;
import com.example.Sheep.infraestructure.mapper.OvejaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OvejaGatewayImpl implements OvejaGateway {
 private final OvejaDataJpaRepository repository;
 private final UsuarioDataJpaRepository usuarioRepository;
 private final OvejaMapper mapper;

 @Override
 public Oveja guardar(Oveja oveja){
 var data = mapper.toData(oveja);
 if (data.getPropietario() != null) {
 Long ownerId = data.getPropietario().getId();
 if (ownerId == null || !usuarioRepository.existsById(ownerId)) {
 throw new DomainValidationException("Propietario no existe");
 }
 data.setPropietario(usuarioRepository.getReferenceById(ownerId));
 }
 var saved = repository.save(data);
 return mapper.toDomain(saved);
 }
 @Override
 public Oveja buscarPorId(Long id){
 return repository.findById(id).map(mapper::toDomain)
 .orElseThrow(() -> new OvejaNotFoundException(id));
 }
 @Override
 public Oveja actualizar(Oveja oveja){
 if(!repository.existsById(oveja.getIdOveja())){
 throw new OvejaNotFoundException(oveja.getIdOveja());
 }
 var data = mapper.toData(oveja);
 if (data.getPropietario() != null) {
 Long ownerId = data.getPropietario().getId();
 if (ownerId == null || !usuarioRepository.existsById(ownerId)) {
 throw new DomainValidationException("Propietario no existe");
 }
 data.setPropietario(usuarioRepository.getReferenceById(ownerId));
 }
 var updated = repository.save(data);
 return mapper.toDomain(updated);
 }
 @Override
 public void eliminar(Long id){
 if(!repository.existsById(id)){
 throw new OvejaNotFoundException(id);
 }
 repository.deleteById(id);
 }
 @Override
 public Oveja buscarPorIdentificacion(String identificacion){
 var data = repository.findByIdentificacion(identificacion);
 return data==null? null: mapper.toDomain(data);
 }
 @Override
 public Page<Oveja> obtenerPaginado(Pageable pageable){
 return repository.findAll(pageable).map(mapper::toDomain);
 }
}
