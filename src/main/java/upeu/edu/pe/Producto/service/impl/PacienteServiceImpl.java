package upeu.edu.pe.Producto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upeu.edu.pe.Producto.entity.Paciente;
import upeu.edu.pe.Producto.repository.PacienteRepository;
import upeu.edu.pe.Producto.service.PacienteService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    
    private final PacienteRepository pacienteRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> obtenerTodos() {
        return pacienteRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> obtenerPorId(@NonNull Long id) {
        return pacienteRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> obtenerPorDni(String dni) {
        return pacienteRepository.findByDni(dni);
    }
    
    @Override
    @Transactional
    public Paciente crear(@NonNull Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    
    @Override
    @Transactional
    public Paciente actualizar(@NonNull Long id, @NonNull Paciente paciente) {
        return pacienteRepository.findById(id)
                .map(pacienteExistente -> {
                    pacienteExistente.setDni(paciente.getDni());
                    pacienteExistente.setNombre(paciente.getNombre());
                    pacienteExistente.setApellidoPaterno(paciente.getApellidoPaterno());
                    pacienteExistente.setApellidoMaterno(paciente.getApellidoMaterno());
                    pacienteExistente.setDireccion(paciente.getDireccion());
                    pacienteExistente.setTelefono(paciente.getTelefono());
                    return pacienteRepository.save(pacienteExistente);
                })
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
    }
    
    @Override
    @Transactional
    public void eliminar(@NonNull Long id) {
        pacienteRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorNombre(String nombre) {
        return pacienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorApellidoPaterno(String apellidoPaterno) {
        return pacienteRepository.findByApellidoPaternoContainingIgnoreCase(apellidoPaterno);
    }
}
