package upeu.edu.pe.Producto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upeu.edu.pe.Producto.entity.HistorialClinica;
import upeu.edu.pe.Producto.repository.HistorialClinicaRepository;
import upeu.edu.pe.Producto.service.HistorialClinicaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistorialClinicaServiceImpl implements HistorialClinicaService {
    
    private final HistorialClinicaRepository historialClinicaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<HistorialClinica> obtenerTodos() {
        return historialClinicaRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<HistorialClinica> obtenerPorId(@NonNull Long id) {
        return historialClinicaRepository.findById(id);
    }
    
    @Override
    @Transactional
    public HistorialClinica crear(@NonNull HistorialClinica historialClinica) {
        return historialClinicaRepository.save(historialClinica);
    }
    
    @Override
    @Transactional
    public HistorialClinica actualizar(@NonNull Long id, @NonNull HistorialClinica historialClinica) {
        return historialClinicaRepository.findById(id)
                .map(historialExistente -> {
                    historialExistente.setPaciente(historialClinica.getPaciente());
                    historialExistente.setMedico(historialClinica.getMedico());
                    historialExistente.setFechaAtencion(historialClinica.getFechaAtencion());
                    historialExistente.setDiagnostico(historialClinica.getDiagnostico());
                    historialExistente.setAnalisis(historialClinica.getAnalisis());
                    historialExistente.setTratamiento(historialClinica.getTratamiento());
                    return historialClinicaRepository.save(historialExistente);
                })
                .orElseThrow(() -> new RuntimeException("Historial clínico no encontrado con id: " + id));
    }
    
    @Override
    @Transactional
    public void eliminar(@NonNull Long id) {
        historialClinicaRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HistorialClinica> obtenerPorPacienteDni(String dni) {
        return historialClinicaRepository.findByPacienteDni(dni);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HistorialClinica> obtenerPorMedicoCmp(String cmp) {
        return historialClinicaRepository.findByMedicoCmp(cmp);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HistorialClinica> obtenerPorFecha(LocalDate fecha) {
        return historialClinicaRepository.findByFechaAtencion(fecha);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HistorialClinica> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return historialClinicaRepository.findByFechaAtencionBetween(fechaInicio, fechaFin);
    }
}
