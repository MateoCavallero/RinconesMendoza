package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Usuario;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.servicios.UsuarioServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioService;

    @GetMapping("/form")
    public String crearUsuario(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Usuario> optional = usuarioService.findAllByQ(id);
            if (optional.isPresent()) {
                model.addAttribute("usuarios", optional.get());
            } else {
                return "redirect:/usuario/list";
            }
        } else {
            model.addAttribute("usuario", new Usuario());
        }
        return "usuario-form";
    }

    @PostMapping("/save")
    public String saveUsuario(Model model, RedirectAttributes redirect, @ModelAttribute Usuario usuario) {
        try {
            usuarioService.crearUsuario(usuario);
            redirect.addFlashAttribute("success", "Usuario guardado con exito");
            return "redirect:/usuario/list";
        } catch (WebException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("persona", usuario);
            return "usuario-form";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String listUsuario(Model model, @RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("usuarios", usuarioService.listAllByQ(q));
        } else {
            model.addAttribute("usuarios", usuarioService.listAll());
        }
        return "usuario-list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String eliminarUsuario(@RequestParam(required = true) String id) {
        usuarioService.deleteById(id);
        return "redirect:/usuario/list";
    }
}
