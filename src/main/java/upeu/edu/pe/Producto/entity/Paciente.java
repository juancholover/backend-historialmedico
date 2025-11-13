package upeu.edu.pe.Producto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un paciente")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del paciente", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @NotBlank(message = "El DNI es obligatorio")
    @Column(nullable = false, unique = true, length = 8)
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    @Schema(description = "DNI del paciente", example = "12345678", required = true)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    @Schema(description = "Nombre del paciente", example = "Juan", required = true)
    private String nombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Column(name = "apellido_paterno", nullable = false, length = 100)
    @Schema(description = "Apellido paterno", example = "Pérez", required = true)
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Column(name = "apellido_materno", nullable = false, length = 100)
    @Schema(description = "Apellido materno", example = "García", required = true)
    private String apellidoMaterno;
    
    @Column(length = 200)
    @Schema(description = "Dirección del paciente", example = "Av. Principal 123, Lima")
    private String direccion;
    
    @Column(length = 15)
    @Schema(description = "Teléfono de contacto", example = "987654321")
    private String telefono;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Historiales clínicos del paciente", accessMode = Schema.AccessMode.READ_ONLY)
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
