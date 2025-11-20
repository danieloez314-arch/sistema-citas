package com.neita.sistemacitas.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidad que representa un profesional en el sistema.
 * Cada profesional está asociado a un usuario y puede gestionar múltiples citas.
 */
@Entity
@Table(name = "profesional")
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String especialidad;

    @Column(name = "horario_disponible")
    private LocalDateTime horarioDisponible;

    @Column(nullable = false)
    private Boolean activo = true;

    // Relación uno a uno con Usuario
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    // Relación uno a muchos con Cita
    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cita> citas = new ArrayList<>();

    /**
     * Constructor por defecto.
     */
    public Profesional() {
    }

    /**
     * Constructor con todos los campos.
     */
    public Profesional(Long id, String especialidad, LocalDateTime horarioDisponible, 
                       Boolean activo, Usuario usuario, List<Cita> citas) {
        this.id = id;
        this.especialidad = especialidad;
        this.horarioDisponible = horarioDisponible;
        this.activo = activo;
        this.usuario = usuario;
        this.citas = citas;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    /**
     * Método auxiliar para agregar una cita al profesional.
     * Mantiene la consistencia bidireccional de la relación.
     */
    public void agregarCita(Cita cita) {
        citas.add(cita);
        cita.setProfesional(this);
    }

    /**
     * Método auxiliar para remover una cita del profesional.
     * Mantiene la consistencia bidireccional de la relación.
     */
    public void removerCita(Cita cita) {
        citas.remove(cita);
        cita.setProfesional(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesional that = (Profesional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Profesional{" +
                "id=" + id +
                ", especialidad='" + especialidad + '\'' +
                ", horarioDisponible=" + horarioDisponible +
                ", activo=" + activo +
                ", usuarioId=" + (usuario != null ? usuario.getId() : null) +
                '}';
    }
}
