package upeu.edu.pe.Producto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.Producto.entity.Paciente;
import upeu.edu.pe.Producto.service.PacienteService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
@Tag(name = "Pacientes", description = "API para la gestión de pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {
    
    private final PacienteService pacienteService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los pacientes")
    public ResponseEntity<List<Paciente>> obtenerTodos() {
        return ResponseEntity.ok(pacienteService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un paciente por ID")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable @NonNull Long id) {
        return pacienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/dni/{dni}")
    @Operation(summary = "Obtener un paciente por DNI")
    public ResponseEntity<Paciente> obtenerPorDni(@PathVariable String dni) {
        return pacienteService.obtenerPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/buscar/nombre/{nombre}")
    @Operation(summary = "Buscar pacientes por nombre")
    public ResponseEntity<List<Paciente>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(pacienteService.buscarPorNombre(nombre));
    }
    
    @GetMapping("/buscar/apellido/{apellido}")
    @Operation(summary = "Buscar pacientes por apellido paterno")
    public ResponseEntity<List<Paciente>> buscarPorApellido(@PathVariable String apellido) {
        return ResponseEntity.ok(pacienteService.buscarPorApellidoPaterno(apellido));
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo paciente")
    public ResponseEntity<Paciente> crear(@Valid @RequestBody @NonNull Paciente paciente) {
        return new ResponseEntity<>(pacienteService.crear(paciente), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un paciente existente")
    public ResponseEntity<Paciente> actualizar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Paciente paciente) {
        try {
            return ResponseEntity.ok(pacienteService.actualizar(id, paciente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un paciente")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
