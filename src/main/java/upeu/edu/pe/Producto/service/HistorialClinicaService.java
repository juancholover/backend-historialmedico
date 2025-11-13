package upeu.edu.pe.Producto.service;

import org.springframework.lang.NonNull;
import upeu.edu.pe.Producto.entity.HistorialClinica;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HistorialClinicaService {
    
    List<HistorialClinica> obtenerTodos();
    
    Optional<HistorialClinica> obtenerPorId(@NonNull Long id);
    
    HistorialClinica crear(@NonNull HistorialClinica historialClinica);
    
    HistorialClinica actualizar(@NonNull Long id, @NonNull HistorialClinica historialClinica);
    
    void eliminar(@NonNull Long id);
    
    List<HistorialClinica> obtenerPorPacienteDni(String dni);
    
    List<HistorialClinica> obtenerPorMedicoCmp(String cmp);
    
    List<HistorialClinica> obtenerPorFecha(LocalDate fecha);
    
    List<HistorialClinica> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
}
