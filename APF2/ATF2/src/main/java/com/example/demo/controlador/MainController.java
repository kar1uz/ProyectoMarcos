package com.example.demo.controlador;

import com.example.demo.modelo.contacto;
import com.example.demo.modelo.registroUsuarios;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contactanos")
    public String contactanos(Model model) {
        if (!model.containsAttribute("contacto")) {
            model.addAttribute("contacto", new contacto());
        }
        return "contactanos";
    }

    @PostMapping("/contactanos")
    public String procesarContacto(@ModelAttribute contacto contacto, RedirectAttributes redirectAttributes) {
        listacontactos.add(contacto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Mensaje enviado correctamente!");
        return "redirect:/contactanos";
    }

    @GetMapping("/iniciarSesion")
    public String iniciarSesion() {
        return "iniciarSesión";
    }

    private List<contacto> listacontactos = new ArrayList<>();

    @GetMapping("/mensajes")
    public String verMensajes(Model model) {
        model.addAttribute("contactos", listacontactos);
        return "mensajes";
    }

    private List<registroUsuarios> listaregistro = new ArrayList<>();

    @GetMapping("/registrate")
    public String registrate(Model model) {
        if (!model.containsAttribute("registroUsuarios")) {
            model.addAttribute("registroUsuarios", new registroUsuarios());
        }
        return "registrate";
    }

    @PostMapping("/registrate")
    public String procesarRegistro(@ModelAttribute registroUsuarios registroUsuarios, RedirectAttributes redirectAttributes) {
        listaregistro.add(registroUsuarios);
        redirectAttributes.addFlashAttribute("mensaje", "¡Registrado correctamente!");
        return "redirect:/registrate";
    }

    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("usuarios", listaregistro);
        return "usuarios";
    }
}
