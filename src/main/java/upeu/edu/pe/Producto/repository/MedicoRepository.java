package upeu.edu.pe.Producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.Producto.entity.Medico;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    Optional<Medico> findByCmp(String cmp);
    
    List<Medico> findByEspecialidad(String especialidad);
    
    List<Medico> findByNombreContainingIgnoreCase(String nombre);
    
    List<Medico> findByApellidosContainingIgnoreCase(String apellidos);
}
