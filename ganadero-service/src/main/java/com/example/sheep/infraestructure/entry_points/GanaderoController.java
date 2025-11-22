package com.example.sheep.infraestructure.entry_points;

import com.example.sheep.domain.model.Ganadero;
import com.example.sheep.domain.usecase.GanaderoUseCase;
import com.example.sheep.infraestructure.dto.GanaderoRequest;
import com.example.sheep.infraestructure.dto.GanaderoResponse;
import com.example.sheep.infraestructure.dto.GanaderoUpdateRequest;
import com.example.sheep.infraestructure.dto.AnalisisNotificacionRequest;
import com.example.sheep.infraestructure.mapper.GanaderoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;

/**
 * Controlador HTTP de Ganadero.
 * Expone endpoints CRUD, paginación y notificación de análisis listo.
 */
@RestController
@RequestMapping("/api/ganadero")
@RequiredArgsConstructor
public class GanaderoController {
 private final GanaderoUseCase useCase;
 private final GanaderoMapper mapper;

 @PostMapping("/save")
 public ResponseEntity<GanaderoResponse> save(@Valid @RequestBody GanaderoRequest request){
 Ganadero saved = useCase.guardar(mapper.toDomain(request));
 return ResponseEntity.created(URI.create("/api/ganadero/" + saved.getId()))
 .body(mapper.toResponse(saved));
 }

 @GetMapping("/{id}")
 public ResponseEntity<GanaderoResponse> findById(@PathVariable Long id){
 var g = useCase.buscarPorId(id);
 return ResponseEntity.ok(mapper.toResponse(g));
 }

 @PutMapping("/update")
 public ResponseEntity<GanaderoResponse> update(@Valid @RequestBody GanaderoUpdateRequest request){
 var updated = useCase.actualizar(mapper.toDomain(request));
 return ResponseEntity.ok(mapper.toResponse(updated));
 }

 @DeleteMapping("/delete/{id}")
 public ResponseEntity<Void> delete(@PathVariable Long id){
 useCase.eliminar(id);
 return ResponseEntity.noContent().build();
 }

 @GetMapping("/buscar-email/{email}")
 public ResponseEntity<GanaderoResponse> findByEmail(@PathVariable String email){
 var g = useCase.buscarPorEmail(email);
 return g!=null? ResponseEntity.ok(mapper.toResponse(g)): ResponseEntity.notFound().build();
 }

 @GetMapping("/all")
 public ResponseEntity<Page<GanaderoResponse>> findAllPaged(@RequestParam(defaultValue="0") int page,
 @RequestParam(defaultValue="10") int size){
 var ganaderos = useCase.obtenerPaginado(PageRequest.of(page, size));
 // En paginación se responde200 con página vacía para evitar error de tipo genérico.
 if(!ganaderos.hasContent()) {
 return ResponseEntity.ok(Page.empty(ganaderos.getPageable()));
 }
 var content = ganaderos.getContent().stream()
 .map(mapper::toResponse)
 .collect(Collectors.toList());
 var pageResp = new PageImpl<>(content, ganaderos.getPageable(), ganaderos.getTotalElements());
 return ResponseEntity.ok(pageResp);
 }

 @PostMapping("/{id}/analisis/notificar")
 public ResponseEntity<Void> notificarAnalisis(@PathVariable Long id, @Valid @RequestBody AnalisisNotificacionRequest body){
 useCase.notificarAnalisisListo(id, body.getReferencia());
 return ResponseEntity.accepted().build();
 }
}

