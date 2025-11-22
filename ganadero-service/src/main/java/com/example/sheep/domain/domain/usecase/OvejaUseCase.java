package com.example.Sheep.domain.domain.usecase;

import com.example.Sheep.domain.exception.DomainValidationException;
import com.example.Sheep.domain.exception.PropietarioNotFoundException;
import com.example.Sheep.domain.model.Oveja;
import com.example.Sheep.domain.model.gateway.GanaderoClientGateway;
import com.example.Sheep.domain.model.gateway.OvejaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Caso de uso de Oveja: orquesta reglas de dominio y persistencia.
 *
 * Reglas clave:
 * - Validaci�n de identificadores y edad en la entidad.
 * - Validaci�n remota de existencia de ganadero cuando se informa ganaderoId.
 */
@RequiredArgsConstructor
@Service
public class OvejaUseCase {
 private final OvejaGateway gateway;
 private final GanaderoClientGateway ganaderoClient;

 public Oveja guardar(Oveja o){
 o.validate();
 // validar existencia del ganadero si viene informado
 if (o.getGanaderoId() != null && !ganaderoClient.existeGanadero(o.getGanaderoId())) {
 throw new PropietarioNotFoundException(o.getGanaderoId());
 }
 return gateway.guardar(o);
 }
 public Oveja buscarPorId(Long id){
 return gateway.buscarPorId(id);
 }
 public Oveja actualizar(Oveja o){
 if(o.getIdOveja()==null){
 throw new DomainValidationException("ID requerido");
 }
 o.validate();
 if (o.getGanaderoId() != null && !ganaderoClient.existeGanadero(o.getGanaderoId())) {
 throw new PropietarioNotFoundException(o.getGanaderoId());
 }
 return gateway.actualizar(o);
 }
 public void eliminar(Long id){
 gateway.eliminar(id);
 }
 public Oveja buscarPorIdentificacion(String identificacion){
 return gateway.buscarPorIdentificacion(identificacion);
 }
 public Page<Oveja> obtenerPaginado(Pageable pageable){
 return gateway.obtenerPaginado(pageable);
 }
 public Page<Oveja> obtenerPorGanadero(Long ganaderoId, Pageable pageable){
 return gateway.obtenerPorGanadero(ganaderoId, pageable);
 }
}

