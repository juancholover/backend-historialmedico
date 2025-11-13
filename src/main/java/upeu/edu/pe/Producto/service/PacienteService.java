package upeu.edu.pe.Producto.service;

import org.springframework.lang.NonNull;
import upeu.edu.pe.Producto.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    
    List<Paciente> obtenerTodos();
    
    Optional<Paciente> obtenerPorId(@NonNull Long id);
    
    Optional<Paciente> obtenerPorDni(String dni);
    
    Paciente crear(@NonNull Paciente paciente);
    
    Paciente actualizar(@NonNull Long id, @NonNull Paciente paciente);
    
    void eliminar(@NonNull Long id);
    
    List<Paciente> buscarPorNombre(String nombre);
    
    List<Paciente> buscarPorApellidoPaterno(String apellidoPaterno);
}
