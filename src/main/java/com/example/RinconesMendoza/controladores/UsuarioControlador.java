package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Usuario;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.servicios.UsuarioServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
                model.addAttribute("usuario", optional.get());
            } else {
                return "redirect:/usuario/list";
            }
        } else {
            model.addAttribute("usuario", new Usuario());
        }
        return "usuario-form";
    }
    
    @PostMapping("/save")
    public String saveUsuario(RedirectAttributes redirect, @ModelAttribute Usuario usuario) {
        try {
            usuarioService.crearUsuario(usuario);
            redirect.addFlashAttribute("success", "Persona guardada con exito");
            return "redirect:/usuario/list";
        } catch (WebException e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuario/list";
        }
    }
    
    @GetMapping("/list")
    public String listAutores(Model model, @RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("usuario", usuarioService.listAllByQ(q));
        }else{
            model.addAttribute("usuario", usuarioService.listAll());
        }
        return "usuario-list";
    }
    
    @GetMapping("/delete")
    public String eliminarUsuario(@RequestParam(required = true) String id) {
        usuarioService.deleteById(id);
        return "redirect:/usuario/list";
    }
}
