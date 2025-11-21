-- Script SQL para insertar datos de prueba en el sistema de citas
-- Este script debe ejecutarse después de que las tablas hayan sido creadas
-- NOTA: Las contraseñas están encriptadas con BCrypt (todas son "password123")

-- Limpiar datos existentes (opcional, comentar si no desea eliminar datos)
-- DELETE FROM cita;
-- DELETE FROM profesional;
-- DELETE FROM servicio;
-- DELETE FROM usuario;

-- Insertar usuarios de prueba
INSERT INTO usuario (nombre, email, password, telefono, fecha_registro, rol, activo) VALUES
('Juan Pérez', 'juan@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '3001234567', NOW(), 'SUPER_ADMIN', true),
('María García', 'maria@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '3007654321', NOW(), 'SUPER_ADMIN', true),
('Carlos López', 'carlos@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '3009876543', NOW(), 'SUPER_ADMIN', true),
('Ana Martínez', 'ana@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '3005556789', NOW(), 'SUPER_ADMIN', true);

-- Insertar servicios de prueba
INSERT INTO servicio (nombre, descripcion, duracion, precio, activo) VALUES
('Corte de Cabello', 'Corte de cabello clásico con acabado profesional', '30 minutos', 25000.00, true),
('Afeitado Clásico', 'Afeitado tradicional con navaja y toalla caliente', '20 minutos', 15000.00, true),
('Corte y Barba', 'Servicio completo de corte de cabello y arreglo de barba', '45 minutos', 35000.00, true),
('Tinte de Cabello', 'Aplicación de tinte profesional con tratamiento', '60 minutos', 45000.00, true),
('Tratamiento Capilar', 'Tratamiento hidratante y revitalizante para el cabello', '40 minutos', 30000.00, true);

-- Insertar profesionales (asociados a usuarios)
-- NOTA: Ajustar los IDs según los usuarios creados
INSERT INTO profesional (especialidad, horario_disponible, activo, usuario_id) VALUES
('Barbero Profesional', '2025-01-15 09:00:00', true, 1),
('Estilista Senior', '2025-01-15 10:00:00', true, 2);

-- Insertar citas de prueba
-- NOTA: Ajustar los IDs según los datos creados
INSERT INTO cita (fecha_hora, estado, notas, fecha_creacion, usuario_id, servicio_id, profesional_id) VALUES
('2025-01-20 10:00:00', 'PENDIENTE', 'Cliente nuevo', NOW(), 3, 1, 1),
('2025-01-20 11:00:00', 'PENDIENTE', 'Cliente regular', NOW(), 4, 3, 2),
('2025-01-21 14:00:00', 'CONFIRMADA', 'Solicita estilo moderno', NOW(), 3, 1, 1),
('2025-01-22 16:00:00', 'PENDIENTE', 'Primera cita', NOW(), 4, 2, 2);

-- Verificar los datos insertados
SELECT 'Usuarios insertados:' as Info, COUNT(*) as Total FROM usuario;
SELECT 'Servicios insertados:' as Info, COUNT(*) as Total FROM servicio;
SELECT 'Profesionales insertados:' as Info, COUNT(*) as Total FROM profesional;
SELECT 'Citas insertadas:' as Info, COUNT(*) as Total FROM cita;
