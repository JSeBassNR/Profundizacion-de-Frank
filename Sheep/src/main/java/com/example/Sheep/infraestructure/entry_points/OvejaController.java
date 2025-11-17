package com.example.Sheep.infraestructure.entry_points;

import com.example.Sheep.domain.model.Oveja;
import com.example.Sheep.domain.usecase.OvejaUseCase;
import com.example.Sheep.infraestructure.drive_adapters.jpa_repository.OvejaData;
import com.example.Sheep.infraestructure.mapper.OvejaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/ovejas")
@RequiredArgsConstructor
public class OvejaController {
 private final OvejaUseCase useCase;
 private final OvejaMapper mapper;

 @PostMapping
 public ResponseEntity<Oveja> save(@RequestBody OvejaData request){
 var saved = useCase.guardar(mapper.toDomain(request));
 return ResponseEntity.created(URI.create("/api/ovejas/" + saved.getIdOveja())).body(saved);
 }
 @GetMapping("/{id}")
 public ResponseEntity<Oveja> findById(@PathVariable Long id){
 var o = useCase.buscarPorId(id);
 return ResponseEntity.ok(o);
 }
 @PutMapping("/{id}")
 public ResponseEntity<Oveja> update(@PathVariable Long id, @RequestBody OvejaData request){
 var domain = mapper.toDomain(request);
 domain.setIdOveja(id);
 var updated = useCase.actualizar(domain);
 return ResponseEntity.ok(updated);
 }
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> delete(@PathVariable Long id){
 useCase.eliminar(id);
 return ResponseEntity.noContent().build();
 }
 @GetMapping("/identificacion/{identificacion}")
 public ResponseEntity<Oveja> findByIdentificador(@PathVariable String identificacion){
 var o = useCase.buscarPorIdentificacion(identificacion);
 return o!=null? ResponseEntity.ok(o): ResponseEntity.notFound().build();
 }
 @GetMapping
 public ResponseEntity<Page<Oveja>> findAllPaged(@RequestParam(defaultValue="0") int page,
 @RequestParam(defaultValue="10") int size){
 var ovejas = useCase.obtenerPaginado(PageRequest.of(page, size));
 if(ovejas.hasContent()) return ResponseEntity.ok(ovejas);
 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
 }
}
