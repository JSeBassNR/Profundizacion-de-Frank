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

@RestController
@RequestMapping("/api/ovejas")
@RequiredArgsConstructor
public class OvejaController {
 private final OvejaUseCase useCase;
 private final OvejaMapper mapper;

 @PostMapping
 public ResponseEntity<Oveja> save(@RequestBody OvejaData request){
 var saved = useCase.guardar(mapper.toDomain(request));
 return ResponseEntity.ok(saved);
 }
 @GetMapping("/{id}")
 public ResponseEntity<Oveja> findById(@PathVariable Long id){
 var o = useCase.buscarPorId(id);
 return o!=null? ResponseEntity.ok(o): ResponseEntity.notFound().build();
 }
 @PutMapping
 public ResponseEntity<Oveja> update(@RequestBody OvejaData request){
 try{
 var updated = useCase.actualizar(mapper.toDomain(request));
 return ResponseEntity.ok(updated);
 }catch(Exception e){
 return ResponseEntity.badRequest().build();
 }
 }
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> delete(@PathVariable Long id){
 try{
 useCase.eliminar(id);
 return ResponseEntity.ok().build();
 }catch(Exception e){
 return ResponseEntity.notFound().build();
 }
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
