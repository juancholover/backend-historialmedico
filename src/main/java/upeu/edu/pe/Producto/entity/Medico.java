package upeu.edu.pe.Producto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un médico")
public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del médico", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @NotBlank(message = "El CMP es obligatorio")
    @Column(nullable = false, unique = true, length = 20)
    @Schema(description = "Código de Colegio Médico del Perú", example = "CMP12345", required = true)
    private String cmp;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    @Schema(description = "Nombre del médico", example = "Dr. Carlos", required = true)
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(nullable = false, length = 200)
    @Schema(description = "Apellidos completos del médico", example = "Rodríguez López", required = true)
    private String apellidos;

    @NotBlank(message = "La especialidad es obligatoria")
    @Column(nullable = false, length = 100)
    @Schema(description = "Especialidad médica", example = "Cardiología", required = true)
    private String especialidad;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Historiales clínicos atendidos", accessMode = Schema.AccessMode.READ_ONLY)
    private List<HistorialClinica> historialesClinicas;
    
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    @Schema(description = "Fecha de registro", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime fechaRegistro;
    
    @Column(name = "fecha_actualizacion")
    @Schema(description = "Fecha de última actualización", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}
