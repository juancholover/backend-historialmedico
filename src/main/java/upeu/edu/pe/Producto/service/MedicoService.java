package upeu.edu.pe.Producto.service;

import org.springframework.lang.NonNull;
import upeu.edu.pe.Producto.entity.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoService {
    
    List<Medico> obtenerTodos();
    
    Optional<Medico> obtenerPorId(@NonNull Long id);
    
    Optional<Medico> obtenerPorCmp(String cmp);
    
    Medico crear(@NonNull Medico medico);
    
    Medico actualizar(@NonNull Long id, @NonNull Medico medico);
    
    void eliminar(@NonNull Long id);
    
    List<Medico> buscarPorEspecialidad(String especialidad);
    
    List<Medico> buscarPorNombre(String nombre);
}
