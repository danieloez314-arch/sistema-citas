package com.neita.sistemacitas.controller;

import com.neita.sistemacitas.dto.ApiResponse;
import com.neita.sistemacitas.dto.RegistroDTO;
import com.neita.sistemacitas.dto.UsuarioDTO;
import com.neita.sistemacitas.entity.Usuario;
import com.neita.sistemacitas.exception.DuplicateResourceException;
import com.neita.sistemacitas.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controlador REST para autenticación y registro de usuarios.
 * Proporciona endpoints para registro y login.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private static final Logger log = LoggerFactory.getLogger(AuthRestController.class);

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor con inyección de dependencias.
     */
    public AuthRestController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UsuarioDTO>> registrar(@Valid @RequestBody RegistroDTO registroDTO) {
        log.info("POST /api/auth/register - Registrando nuevo usuario con email: {}", registroDTO.getEmail());

        // Validar que las contraseñas coincidan
        if (!registroDTO.getPassword().equals(registroDTO.getConfirmPassword())) {
            log.warn("Las contraseñas no coinciden para el email: {}", registroDTO.getEmail());
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Las contraseñas no coinciden"));
        }

        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
            log.warn("Intento de registro con email duplicado: {}", registroDTO.getEmail());
            throw new DuplicateResourceException("Ya existe un usuario con el email: " + registroDTO.getEmail());
        }

        // Crear el nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        usuario.setTelefono(registroDTO.getTelefono());
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setRol("SUPER_ADMIN"); // Por defecto todos son SUPER_ADMIN según el sistema actual
        usuario.setActivo(true);

        Usuario guardado = usuarioRepository.save(usuario);
        log.info("Usuario registrado exitosamente con ID: {}", guardado.getId());

        // Convertir a DTO para la respuesta (sin incluir la contraseña)
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(guardado.getId());
        usuarioDTO.setNombre(guardado.getNombre());
        usuarioDTO.setEmail(guardado.getEmail());
        usuarioDTO.setTelefono(guardado.getTelefono());
        usuarioDTO.setFechaRegistro(guardado.getFechaRegistro());
        usuarioDTO.setRol(guardado.getRol());
        usuarioDTO.setActivo(guardado.getActivo());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Usuario registrado exitosamente", usuarioDTO));
    }

    /**
     * Endpoint de información sobre el estado de autenticación.
     */
    @GetMapping("/status")
    public ResponseEntity<ApiResponse<String>> status() {
        log.debug("GET /api/auth/status - Verificando estado de autenticación");
        return ResponseEntity.ok(ApiResponse.success("Sistema de autenticación activo", "OK"));
    }
}
