package com.neita.sistemacitas.controller;

import com.neita.sistemacitas.repository.CitaRepository;
import com.neita.sistemacitas.repository.ProfesionalRepository;
import com.neita.sistemacitas.repository.ServicioRepository;
import com.neita.sistemacitas.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador principal para la página de inicio y rutas generales.
 */
@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private final CitaRepository citaRepository;
    private final ServicioRepository servicioRepository;
    private final ProfesionalRepository profesionalRepository;
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor con inyección de dependencias.
     */
    public HomeController(CitaRepository citaRepository, ServicioRepository servicioRepository,
                          ProfesionalRepository profesionalRepository, UsuarioRepository usuarioRepository) {
        this.citaRepository = citaRepository;
        this.servicioRepository = servicioRepository;
        this.profesionalRepository = profesionalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Página de inicio con estadísticas generales.
     */
    @GetMapping("/")
    public String index(Model model) {
        log.info("Accediendo a la página de inicio");
        
        model.addAttribute("title", "Inicio");
        model.addAttribute("activeMenu", "home");
        model.addAttribute("totalCitas", citaRepository.count());
        model.addAttribute("totalServicios", servicioRepository.countServiciosActivos());
        model.addAttribute("totalProfesionales", profesionalRepository.countProfesionalesActivos());
        model.addAttribute("totalUsuarios", usuarioRepository.countUsuariosActivos());
        
        return "index";
    }

    /**
     * Página de login.
     */
    @GetMapping("/login")
    public String login(Model model) {
        log.info("Accediendo a la página de login");
        model.addAttribute("title", "Iniciar Sesión");
        return "auth/login";
    }

    /**
     * Página de acceso denegado.
     */
    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        log.warn("Acceso denegado");
        model.addAttribute("title", "Acceso Denegado");
        return "error/access-denied";
    }
}
