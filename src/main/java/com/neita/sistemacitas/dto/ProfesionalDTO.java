package com.neita.sistemacitas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * DTO para transferir datos de Profesional entre capas.
 * Incluye validaciones para garantizar la integridad de los datos.
 */
public class ProfesionalDTO {

    private Long id;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(min = 2, max = 255, message = "La especialidad debe tener entre 2 y 255 caracteres")
    private String especialidad;

    private LocalDateTime horarioDisponible;

    private Boolean activo;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    private String usuarioNombre;

    private String usuarioEmail;

    /**
     * Constructor por defecto.
     */
    public ProfesionalDTO() {
    }

    /**
     * Constructor con todos los campos.
     */
    public ProfesionalDTO(Long id, String especialidad, LocalDateTime horarioDisponible, 
                          Boolean activo, Long usuarioId, String usuarioNombre, String usuarioEmail) {
        this.id = id;
        this.especialidad = especialidad;
        this.horarioDisponible = horarioDisponible;
        this.activo = activo;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.usuarioEmail = usuarioEmail;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDateTime getHorarioDisponible() {
        return horarioDisponible;
    }

    public void setHorarioDisponible(LocalDateTime horarioDisponible) {
        this.horarioDisponible = horarioDisponible;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }
}
