package upeu.edu.pe.Producto.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_clinica")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa el historial clínico de un paciente")
public class HistorialClinica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del historial", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_dni", referencedColumnName = "dni", nullable = false)
    @NotNull(message = "El paciente es obligatorio")
    @Schema(description = "Paciente asociado al historial", required = true)
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_cmp", referencedColumnName = "cmp", nullable = false)
    @NotNull(message = "El médico es obligatorio")
    @Schema(description = "Médico que atiende", required = true)
    private Medico medico;
    
    @NotNull(message = "La fecha de atención es obligatoria")
    @Column(name = "fecha_atencion", nullable = false)
    @Schema(description = "Fecha de la atención médica", example = "2024-01-15", required = true)
    private LocalDate fechaAtencion;
    
    @NotBlank(message = "El diagnóstico es obligatorio")
    @Column(nullable = false, length = 500)
    @Schema(description = "Diagnóstico médico", example = "Hipertensión arterial", required = true)
    private String diagnostico;

    @Column(length = 1000)
    @Schema(description = "Análisis detallado", example = "Paciente presenta presión arterial elevada")
    private String analisis;

    @Column(length = 1000)
    @Schema(description = "Tratamiento prescrito", example = "Enalapril 10mg cada 12 horas")
    private String tratamiento;
    
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    @Schema(description = "Fecha de registro del historial", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime fechaRegistro;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }
}
