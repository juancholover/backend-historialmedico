package upeu.edu.pe.Producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.Producto.entity.Paciente;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    Optional<Paciente> findByDni(String dni);
    
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);
    
    List<Paciente> findByApellidoPaternoContainingIgnoreCase(String apellidoPaterno);
    
    List<Paciente> findByApellidoMaternoContainingIgnoreCase(String apellidoMaterno);
}
