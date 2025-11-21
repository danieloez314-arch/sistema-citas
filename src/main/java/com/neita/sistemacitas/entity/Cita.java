package com.neita.sistemacitas.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa una cita en el sistema.
 * Una cita está asociada a un usuario, un profesional y un servicio.
 */
@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false, length = 50)
    private String estado = "PENDIENTE";

    @Column(columnDefinition = "TEXT")
    private String notas;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // Relación muchos a uno con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relación muchos a uno con Servicio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    // Relación muchos a uno con Profesional
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_id", nullable = false)
    private Profesional profesional;

    /**
     * Constructor por defecto.
     */
    public Cita() {
    }

    /**
     * Constructor con todos los campos.
     */
    public Cita(Long id, LocalDateTime fechaHora, String estado, String notas, 
                LocalDateTime fechaCreacion, Usuario usuario, Servicio servicio, 
                Profesional profesional) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.notas = notas;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.servicio = servicio;
        this.profesional = profesional;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    /**
     * Método que se ejecuta antes de persistir la entidad.
     * Establece la fecha de creación automáticamente.
     */
    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
        if (estado == null || estado.isEmpty()) {
            estado = "PENDIENTE";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return Objects.equals(id, cita.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", estado='" + estado + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", usuarioId=" + (usuario != null ? usuario.getId() : null) +
                ", servicioId=" + (servicio != null ? servicio.getId() : null) +
                ", profesionalId=" + (profesional != null ? profesional.getId() : null) +
                '}';
    }
}
