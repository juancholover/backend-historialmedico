package upeu.edu.pe.Producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.Producto.entity.HistorialClinica;
import upeu.edu.pe.Producto.entity.Medico;
import upeu.edu.pe.Producto.entity.Paciente;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialClinicaRepository extends JpaRepository<HistorialClinica, Long> {
    
    List<HistorialClinica> findByPaciente(Paciente paciente);
    
    List<HistorialClinica> findByPacienteDni(String dni);
    
    List<HistorialClinica> findByMedico(Medico medico);
    
    List<HistorialClinica> findByMedicoCmp(String cmp);
    
    List<HistorialClinica> findByFechaAtencion(LocalDate fechaAtencion);
    
    List<HistorialClinica> findByFechaAtencionBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
