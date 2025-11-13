package upeu.edu.pe.Producto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upeu.edu.pe.Producto.entity.Medico;
import upeu.edu.pe.Producto.repository.MedicoRepository;
import upeu.edu.pe.Producto.service.MedicoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {
    
    private final MedicoRepository medicoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Medico> obtenerTodos() {
        return medicoRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Medico> obtenerPorId(@NonNull Long id) {
        return medicoRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Medico> obtenerPorCmp(String cmp) {
        return medicoRepository.findByCmp(cmp);
    }
    
    @Override
    @Transactional
    public Medico crear(@NonNull Medico medico) {
        return medicoRepository.save(medico);
    }
    
    @Override
    @Transactional
    public Medico actualizar(@NonNull Long id, @NonNull Medico medico) {
        return medicoRepository.findById(id)
                .map(medicoExistente -> {
                    medicoExistente.setCmp(medico.getCmp());
                    medicoExistente.setNombre(medico.getNombre());
                    medicoExistente.setApellidos(medico.getApellidos());
                    medicoExistente.setEspecialidad(medico.getEspecialidad());
                    return medicoRepository.save(medicoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con id: " + id));
    }
    
    @Override
    @Transactional
    public void eliminar(@NonNull Long id) {
        medicoRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Medico> buscarPorEspecialidad(String especialidad) {
        return medicoRepository.findByEspecialidad(especialidad);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Medico> buscarPorNombre(String nombre) {
        return medicoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
