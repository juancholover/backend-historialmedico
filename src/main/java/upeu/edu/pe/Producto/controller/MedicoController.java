package upeu.edu.pe.Producto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.Producto.entity.Medico;
import upeu.edu.pe.Producto.service.MedicoService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
@Tag(name = "Médicos", description = "API para la gestión de médicos")
@CrossOrigin(origins = "*")
public class MedicoController {
    
    private final MedicoService medicoService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los médicos")
    public ResponseEntity<List<Medico>> obtenerTodos() {
        return ResponseEntity.ok(medicoService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un médico por ID")
    public ResponseEntity<Medico> obtenerPorId(@PathVariable @NonNull Long id) {
        return medicoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/cmp/{cmp}")
    @Operation(summary = "Obtener un médico por CMP")
    public ResponseEntity<Medico> obtenerPorCmp(@PathVariable String cmp) {
        return medicoService.obtenerPorCmp(cmp)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/especialidad/{especialidad}")
    @Operation(summary = "Buscar médicos por especialidad")
    public ResponseEntity<List<Medico>> buscarPorEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(medicoService.buscarPorEspecialidad(especialidad));
    }
    
    @GetMapping("/buscar/nombre/{nombre}")
    @Operation(summary = "Buscar médicos por nombre")
    public ResponseEntity<List<Medico>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(medicoService.buscarPorNombre(nombre));
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo médico")
    public ResponseEntity<Medico> crear(@Valid @RequestBody @NonNull Medico medico) {
        return new ResponseEntity<>(medicoService.crear(medico), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un médico existente")
    public ResponseEntity<Medico> actualizar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Medico medico) {
        try {
            return ResponseEntity.ok(medicoService.actualizar(id, medico));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un médico")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        medicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
