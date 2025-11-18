package com.neita.sistemacitas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para transferir datos de Usuario entre capas.
 * Incluye validaciones para garantizar la integridad de los datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Size(max = 255, message = "El email no puede exceder 255 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 255, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @Size(max = 255, message = "El teléfono no puede exceder 255 caracteres")
    private String telefono;

    private LocalDateTime fechaRegistro;

    private String rol;

    private Boolean activo;

    /**
     * Constructor sin contraseña para respuestas (no exponer contraseña).
     */
    public UsuarioDTO(Long id, String nombre, String email, String telefono, 
                      LocalDateTime fechaRegistro, String rol, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
        this.activo = activo;
    }
}
