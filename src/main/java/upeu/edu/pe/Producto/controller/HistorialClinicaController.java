package upeu.edu.pe.Producto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.Producto.entity.HistorialClinica;
import upeu.edu.pe.Producto.service.HistorialClinicaService;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/historiales")
@RequiredArgsConstructor
@Tag(name = "Historiales Clínicos", description = "API para la gestión de historiales clínicos")
@CrossOrigin(origins = "*")
public class HistorialClinicaController {
    
    private final HistorialClinicaService historialClinicaService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los historiales clínicos")
    public ResponseEntity<List<HistorialClinica>> obtenerTodos() {
        return ResponseEntity.ok(historialClinicaService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un historial clínico por ID")
    public ResponseEntity<HistorialClinica> obtenerPorId(@PathVariable @NonNull Long id) {
        return historialClinicaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/paciente/{dni}")
    @Operation(summary = "Obtener historiales clínicos por DNI del paciente")
    public ResponseEntity<List<HistorialClinica>> obtenerPorPacienteDni(@PathVariable String dni) {
        return ResponseEntity.ok(historialClinicaService.obtenerPorPacienteDni(dni));
    }
    
    @GetMapping("/medico/{cmp}")
    @Operation(summary = "Obtener historiales clínicos por CMP del médico")
    public ResponseEntity<List<HistorialClinica>> obtenerPorMedicoCmp(@PathVariable String cmp) {
        return ResponseEntity.ok(historialClinicaService.obtenerPorMedicoCmp(cmp));
    }
    
    @GetMapping("/fecha/{fecha}")
    @Operation(summary = "Obtener historiales clínicos por fecha de atención")
    public ResponseEntity<List<HistorialClinica>> obtenerPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(historialClinicaService.obtenerPorFecha(fecha));
    }
    
    @GetMapping("/rango-fechas")
    @Operation(summary = "Obtener historiales clínicos por rango de fechas")
    public ResponseEntity<List<HistorialClinica>> obtenerPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(historialClinicaService.obtenerPorRangoFechas(fechaInicio, fechaFin));
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo historial clínico")
    public ResponseEntity<HistorialClinica> crear(@Valid @RequestBody @NonNull HistorialClinica historialClinica) {
        return new ResponseEntity<>(historialClinicaService.crear(historialClinica), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un historial clínico existente")
    public ResponseEntity<HistorialClinica> actualizar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull HistorialClinica historialClinica) {
        try {
            return ResponseEntity.ok(historialClinicaService.actualizar(id, historialClinica));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un historial clínico")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        historialClinicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
