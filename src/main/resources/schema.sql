-- Script SQL para crear el esquema de base de datos del sistema de citas
-- Este script es opcional ya que Hibernate puede crear las tablas automáticamente
-- Sin embargo, se proporciona para referencia y para entornos de producción

CREATE DATABASE IF NOT EXISTS Barberia_Neita
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE Barberia_Neita;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(255),
    fecha_registro DATETIME(6) NOT NULL,
    rol VARCHAR(50) NOT NULL DEFAULT 'SUPER_ADMIN',
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    INDEX idx_email (email),
    INDEX idx_activo (activo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de servicios
CREATE TABLE IF NOT EXISTS servicio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    duracion VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    INDEX idx_activo (activo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de profesionales
CREATE TABLE IF NOT EXISTS profesional (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    especialidad VARCHAR(255) NOT NULL,
    horario_disponible DATETIME(6),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    usuario_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    INDEX idx_activo (activo),
    INDEX idx_usuario_id (usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de citas
CREATE TABLE IF NOT EXISTS cita (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora DATETIME(6) NOT NULL,
    estado VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
    notas TEXT,
    fecha_creacion DATETIME(6) NOT NULL,
    usuario_id BIGINT NOT NULL,
    servicio_id BIGINT NOT NULL,
    profesional_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (servicio_id) REFERENCES servicio(id) ON DELETE CASCADE,
    FOREIGN KEY (profesional_id) REFERENCES profesional(id) ON DELETE CASCADE,
    INDEX idx_fecha_hora (fecha_hora),
    INDEX idx_estado (estado),
    INDEX idx_usuario_id (usuario_id),
    INDEX idx_servicio_id (servicio_id),
    INDEX idx_profesional_id (profesional_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
