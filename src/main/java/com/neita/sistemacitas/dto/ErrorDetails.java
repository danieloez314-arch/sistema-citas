package com.neita.sistemacitas.dto;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Clase para detalles de errores en respuestas de la API.
 * Proporciona información estructurada sobre errores de validación y excepciones.
 */
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;
    private Map<String, String> errors;

    /**
     * Constructor por defecto.
     */
    public ErrorDetails() {
    }

    /**
     * Constructor con todos los campos.
     */
    public ErrorDetails(LocalDateTime timestamp, String message, String details, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errors = errors;
    }

    /**
     * Constructor para errores simples sin detalles de validación.
     */
    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters y Setters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
