package com.neita.sistemacitas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * DTO para transferir datos de Cita entre capas.
 * Incluye validaciones para garantizar la integridad de los datos.
 */
public class CitaDTO {

    private Long id;

    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La fecha y hora deben ser futuras")
    private LocalDateTime fechaHora;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50, message = "El estado no puede exceder 50 caracteres")
    private String estado;

    @Size(max = 1000, message = "Las notas no pueden exceder 1000 caracteres")
    private String notas;

    private LocalDateTime fechaCreacion;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    private String usuarioNombre;

    @NotNull(message = "El ID del servicio es obligatorio")
    private Long servicioId;

    private String servicioNombre;

    private Double servicioPrecio;

    @NotNull(message = "El ID del profesional es obligatorio")
    private Long profesionalId;

    private String profesionalNombre;

    private String profesionalEspecialidad;

    /**
     * Constructor por defecto.
     */
    public CitaDTO() {
    }

    /**
     * Constructor con todos los campos.
     */
    public CitaDTO(Long id, LocalDateTime fechaHora, String estado, String notas, 
                   LocalDateTime fechaCreacion, Long usuarioId, String usuarioNombre, 
                   Long servicioId, String servicioNombre, Double servicioPrecio, 
                   Long profesionalId, String profesionalNombre, String profesionalEspecialidad) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.notas = notas;
        this.fechaCreacion = fechaCreacion;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.servicioId = servicioId;
        this.servicioNombre = servicioNombre;
        this.servicioPrecio = servicioPrecio;
        this.profesionalId = profesionalId;
        this.profesionalNombre = profesionalNombre;
        this.profesionalEspecialidad = profesionalEspecialidad;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public String getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public Double getServicioPrecio() {
        return servicioPrecio;
    }

    public void setServicioPrecio(Double servicioPrecio) {
        this.servicioPrecio = servicioPrecio;
    }

    public Long getProfesionalId() {
        return profesionalId;
    }

    public void setProfesionalId(Long profesionalId) {
        this.profesionalId = profesionalId;
    }

    public String getProfesionalNombre() {
        return profesionalNombre;
    }

    public void setProfesionalNombre(String profesionalNombre) {
        this.profesionalNombre = profesionalNombre;
    }

    public String getProfesionalEspecialidad() {
        return profesionalEspecialidad;
    }

    public void setProfesionalEspecialidad(String profesionalEspecialidad) {
        this.profesionalEspecialidad = profesionalEspecialidad;
    }
}
