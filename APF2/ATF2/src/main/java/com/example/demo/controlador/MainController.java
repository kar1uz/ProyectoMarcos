package com.example.demo.controlador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.ContactoForm;
import com.example.demo.dto.ContratoForm;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.PlanForm;
import com.example.demo.dto.RegistroRequest;
import com.example.demo.modelo.Administrador;
import com.example.demo.modelo.Contacto;
import com.example.demo.modelo.Contrato;
import com.example.demo.modelo.Plan;
import com.example.demo.modelo.Usuario;
import com.example.demo.repository.AdministradorRepository;
import com.example.demo.repository.ContactoRepository;
import com.example.demo.repository.ContratoRepository;
import com.example.demo.repository.PlanRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.AuthService;

import jakarta.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    private static final BigDecimal COSTO_INSTALACION_FIJO = new BigDecimal("70.00");

    @ModelAttribute("usuarioLogueado")
    public Usuario getUsuarioLogueadoParaHeader() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String
                        && authentication.getPrincipal().equals("anonymousUser"))) {

            if (authentication.getPrincipal() instanceof UserDetails userDetails) {
                String email = userDetails.getUsername();

                Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
                if (usuarioOptional.isPresent()) {
                    return usuarioOptional.get();
                }

                Optional<Administrador> adminOptional = administradorRepository.findByEmail(email);
                if (adminOptional.isPresent()) {
                    Administrador admin = adminOptional.get();
                    Usuario usuarioParaHeader = new Usuario();
                    usuarioParaHeader.setNombre(admin.getNombre());
                    usuarioParaHeader.setApellido(admin.getApellido());
                    usuarioParaHeader.setEmail(admin.getEmail());
                    return usuarioParaHeader;
                }
            }
        }
        return null;
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/iniciarSesion";
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Plan> planesActivos = planRepository.findByActivoTrue();
        model.addAttribute("planes", planesActivos);

        ContratoForm contratoForm;
        if (model.containsAttribute("contratoForm")) {
            contratoForm = (ContratoForm) model.getAttribute("contratoForm");
        } else {
            contratoForm = new ContratoForm();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String
                        && authentication.getPrincipal().equals("anonymousUser"))) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                String username = userDetails.getUsername();
                Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
                usuarioOptional.ifPresent(usuario -> {
                    contratoForm.setNombreUsuario(usuario.getNombre());
                    contratoForm.setApellidoUsuario(usuario.getApellido());
                    contratoForm.setEmailUsuario(usuario.getEmail());
                });
            }
        }

        contratoForm.setCostoInstalacion(COSTO_INSTALACION_FIJO);

        model.addAttribute("contratoForm", contratoForm);

        if (model.containsAttribute("showContratarModal")) {
            model.addAttribute("showContratarModal", true);
        }
        if (model.containsAttribute("selectedPlanId")) {
            model.addAttribute("selectedPlanId", model.getAttribute("selectedPlanId"));
        }

        return "index";
    }

    @GetMapping("/indexAdmin")
    public String indexAdmin() {
        return "indexAdmin";
    }

    @GetMapping("/contactanos")
    public String mostrarFormularioContacto(Model model) {
        ContactoForm contactoForm;

        if (model.containsAttribute("contactoForm")) {
            contactoForm = (ContactoForm) model.getAttribute("contactoForm");
        } else {
            contactoForm = new ContactoForm();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() &&
                    !(authentication.getPrincipal() instanceof String
                            && authentication.getPrincipal().equals("anonymousUser"))) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails userDetails) {
                    String username = userDetails.getUsername();
                    Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
                    usuarioOptional.ifPresent(usuario -> {
                        contactoForm.setNombre(usuario.getNombre());
                        contactoForm.setApellido(usuario.getApellido());
                        contactoForm.setEmail(usuario.getEmail());
                    });
                }
            }
        }
        model.addAttribute("contactoForm", contactoForm);
        return "contactanos";
    }

    @PostMapping("/contactanos")
    public String procesarContactanos(@ModelAttribute @Valid ContactoForm contactoForm, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "contactanos";
        }

        try {
            Contacto nuevoContacto = new Contacto();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() &&
                    !(authentication.getPrincipal() instanceof String
                            && authentication.getPrincipal().equals("anonymousUser"))) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails userDetails) {
                    String username = userDetails.getUsername();
                    Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
                    usuarioOptional.ifPresent(nuevoContacto::setUsuario);
                }
            }
            nuevoContacto.setNombreRemitente(contactoForm.getNombre());
            nuevoContacto.setApellidoRemitente(contactoForm.getApellido());
            nuevoContacto.setEmailRemitente(contactoForm.getEmail());
            nuevoContacto.setTelefonoRemitente(contactoForm.getTelefono());
            nuevoContacto.setAsunto(contactoForm.getAsunto());
            nuevoContacto.setMensaje(contactoForm.getMensaje());

            nuevoContacto.setAsesorGenero(Contacto.AsesorGenero.valueOf(contactoForm.getAsesorGenero().toUpperCase()));

            contactoRepository.save(nuevoContacto);

            redirectAttributes.addFlashAttribute("mensaje", "¡Gracias por contactarnos! Tu mensaje ha sido enviado.");
            return "redirect:/contactanos";

        } catch (IllegalArgumentException e) {
            result.rejectValue("asesorGenero", "error.contactoForm", "Valor de género de asesor no válido.");
            return "contactanos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al enviar tu mensaje. Inténtalo de nuevo.");
            return "redirect:/contactanos";
        }
    }

    @GetMapping("/iniciarSesion")
    public String iniciarSesion(Model model,
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout) {
        if (!model.containsAttribute("loginRequest")) {
            model.addAttribute("loginRequest", new LoginRequest());
        }
        if (error != null) {
            model.addAttribute("error", "Credenciales inválidas. Por favor, inténtelo de nuevo.");
        }
        if (logout != null) {
            model.addAttribute("mensaje", "Ha cerrado sesión exitosamente.");
        }
        return "iniciarSesion";
    }

    @GetMapping("/postLoginRedirect")
    public String postLoginRedirect() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        if (isAdmin) {
            return "redirect:/indexAdmin";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/registrate")
    public String registrate(Model model) {
        if (!model.containsAttribute("registroRequest")) {
            model.addAttribute("registroRequest", new RegistroRequest());
        }
        return "registrate";
    }

    @PostMapping("/registrate")
    public String procesarRegistro(@ModelAttribute @Valid RegistroRequest registroRequest, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registroRequest",
                    result);
            redirectAttributes.addFlashAttribute("registroRequest", registroRequest);
            return "redirect:/registrate";
        }

        try {
            Usuario nuevoUsuario = authService.registrarUsuario(registroRequest);
            if (nuevoUsuario != null) {
                redirectAttributes.addFlashAttribute("mensaje",
                        "¡Registrado correctamente! Ahora puedes iniciar sesión.");
                return "redirect:/iniciarSesion";
            } else {
                redirectAttributes.addFlashAttribute("error",
                        "El correo electrónico ya está en uso. Por favor, intente con otro.");
                return "redirect:/registrate";
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/registrate";
        }
    }

    @GetMapping("/mensajes")
    public String verMensajes(Model model) {
        model.addAttribute("contactos", contactoRepository.findAll());
        return "mensajes";
    }

    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("/serviciosAdmin")
    public String verServiciosAdmin(Model model) {
        if (!model.containsAttribute("planForm")) {
            model.addAttribute("planForm", new PlanForm());
        }
        model.addAttribute("planes", planRepository.findAll());
        return "serviciosAdmin";
    }

    @PostMapping("/serviciosAdmin/guardar")
    public String guardarPlan(@ModelAttribute @Valid PlanForm planForm, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.planForm", result);
            redirectAttributes.addFlashAttribute("planForm", planForm);
            redirectAttributes.addFlashAttribute("errorForm", "Hubo errores en el formulario de creación.");
            return "redirect:/serviciosAdmin";
        }

        try {
            Plan plan = new Plan();
            plan.setNombrePlan(planForm.getNombrePlan());
            plan.setVelocidadDescargaMbps(planForm.getVelocidadDescargaMbps());
            plan.setVelocidadCargaMbps(planForm.getVelocidadCargaMbps());
            plan.setWifiIncluido(planForm.getWifiIncluido());
            plan.setMesGratisPromocion(planForm.getMesGratisPromocion());
            plan.setPuertosEthernet(planForm.getPuertosEthernet());
            plan.setPrecioMensual(planForm.getPrecioMensual());
            plan.setDescripcion(planForm.getDescripcion());
            plan.setActivo(planForm.getActivo());

            planRepository.save(plan);
            redirectAttributes.addFlashAttribute("mensaje", "Plan guardado exitosamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el plan: " + e.getMessage());
        }
        return "redirect:/serviciosAdmin";
    }

    @PostMapping("/serviciosAdmin/eliminar")
    public String eliminarPlan(@RequestParam("id") Integer idPlan, RedirectAttributes redirectAttributes) {
        Optional<Plan> planOptional = planRepository.findById(idPlan);
        if (!planOptional.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "El plan no existe.");
            return "redirect:/serviciosAdmin";
        }

        long contratosActivos = contratoRepository.countByPlanIdPlanAndEstadoContrato(idPlan,
                Contrato.EstadoContrato.ACTIVO);
        long contratosPendientes = contratoRepository.countByPlanIdPlanAndEstadoContrato(idPlan,
                Contrato.EstadoContrato.PENDIENTE_INSTALACION);

        long contratosQueImpidenEliminacion = contratosActivos + contratosPendientes;

        if (contratosQueImpidenEliminacion > 0) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede eliminar este plan porque tiene " + contratosQueImpidenEliminacion
                            + " contratos asociados que impiden su eliminación (activos o pendientes de instalación).");
            return "redirect:/serviciosAdmin";
        }

        try {
            planRepository.deleteById(idPlan);
            redirectAttributes.addFlashAttribute("mensaje", "Plan eliminado exitosamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el plan: " + e.getMessage());
        }
        return "redirect:/serviciosAdmin";
    }

    @PostMapping("/serviciosAdmin/actualizar")
    public String actualizarPlan(@ModelAttribute @Valid PlanForm planForm, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.planForm", result);
            redirectAttributes.addFlashAttribute("planForm", planForm);
            redirectAttributes.addFlashAttribute("errorModal", "Hubo errores en el formulario de actualización.");
            redirectAttributes.addFlashAttribute("showUpdateModal", true);
            return "redirect:/serviciosAdmin";
        }

        try {
            Optional<Plan> existingPlanOpt = planRepository.findById(planForm.getIdPlan());
            if (existingPlanOpt.isPresent()) {
                Plan plan = existingPlanOpt.get();
                plan.setNombrePlan(planForm.getNombrePlan());
                plan.setVelocidadDescargaMbps(planForm.getVelocidadDescargaMbps());
                plan.setVelocidadCargaMbps(planForm.getVelocidadCargaMbps());
                plan.setWifiIncluido(planForm.getWifiIncluido());
                plan.setMesGratisPromocion(planForm.getMesGratisPromocion());
                plan.setPuertosEthernet(planForm.getPuertosEthernet());
                plan.setPrecioMensual(planForm.getPrecioMensual());
                plan.setDescripcion(planForm.getDescripcion());
                plan.setActivo(planForm.getActivo());

                planRepository.save(plan);
                redirectAttributes.addFlashAttribute("mensaje", "Plan actualizado exitosamente!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Plan no encontrado para actualizar.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el plan: " + e.getMessage());
        }
        return "redirect:/serviciosAdmin";
    }

    @GetMapping("/contratos")
    public String verContratos(Model model) {
        List<Contrato> contratos = contratoRepository.findAllWithUsuarioAndPlan();
        model.addAttribute("contratos", contratos);
        return "contratos";
    }

    @PostMapping("/contratarPlan")
    public String contratarPlan(@ModelAttribute @Valid ContratoForm contratoForm,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                (authentication.getPrincipal() instanceof String
                        && authentication.getPrincipal().equals("anonymousUser"))) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para contratar un plan.");
            return "redirect:/iniciarSesion";
        }

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);

        if (!usuarioOptional.isPresent()) {
            redirectAttributes.addFlashAttribute("error",
                    "No se encontró el usuario. Por favor, inicie sesión nuevamente.");
            return "redirect:/iniciarSesion";
        }
        Usuario usuario = usuarioOptional.get();

        if (result.hasErrors()) {
            contratoForm.setCostoInstalacion(COSTO_INSTALACION_FIJO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contratoForm", result);
            redirectAttributes.addFlashAttribute("contratoForm", contratoForm);
            redirectAttributes.addFlashAttribute("errorContratoForm",
                    "Por favor, corrige los errores en el formulario.");
            redirectAttributes.addFlashAttribute("showContratarModal", true);
            redirectAttributes.addFlashAttribute("selectedPlanId", contratoForm.getIdPlan());
            return "redirect:/index#planes";
        }

        try {
            Optional<Plan> planOptional = planRepository.findById(contratoForm.getIdPlan());
            if (!planOptional.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "El plan seleccionado no es válido.");
                redirectAttributes.addFlashAttribute("showContratarModal", true);
                redirectAttributes.addFlashAttribute("selectedPlanId", contratoForm.getIdPlan());
                return "redirect:/index#planes";
            }
            Plan plan = planOptional.get();

            Contrato nuevoContrato = new Contrato();
            nuevoContrato.setUsuario(usuario);
            nuevoContrato.setPlan(plan);

            nuevoContrato.setDireccionInstalacion(contratoForm.getDireccionInstalacion());
            nuevoContrato.setNumeroTelefonoContacto(contratoForm.getNumeroTelefonoContacto());
            nuevoContrato.setMetodoPago(contratoForm.getMetodoPago());
            nuevoContrato.setCostoInstalacion(COSTO_INSTALACION_FIJO);
            nuevoContrato.setObservaciones(contratoForm.getObservaciones());

            LocalDate fechaInicioReal = (contratoForm.getFechaInicioServicio() != null)
                    ? contratoForm.getFechaInicioServicio()
                    : LocalDate.now();
            nuevoContrato.setFechaInicioServicio(fechaInicioReal);

            LocalDate fechaFinCalculada = fechaInicioReal.plusYears(1);
            nuevoContrato.setFechaFinContrato(fechaFinCalculada);

            contratoRepository.save(nuevoContrato);

            redirectAttributes.addFlashAttribute("mensaje",
                    "¡Contrato realizado con éxito! Nos pondremos en contacto pronto.");
            return "redirect:/index#planes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al procesar la contratación: " + e.getMessage());
            contratoForm.setCostoInstalacion(COSTO_INSTALACION_FIJO);
            redirectAttributes.addFlashAttribute("contratoForm", contratoForm);
            redirectAttributes.addFlashAttribute("showContratarModal", true);
            redirectAttributes.addFlashAttribute("selectedPlanId", contratoForm.getIdPlan());
            return "redirect:/index#planes";
        }
    }
}