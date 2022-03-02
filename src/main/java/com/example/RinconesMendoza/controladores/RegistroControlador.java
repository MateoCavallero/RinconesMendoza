package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.servicios.UsuarioSeguridadServicio;
import com.example.RinconesMendoza.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registro")
public class RegistroControlador {

    @Autowired
    private UsuarioSeguridadServicio userSerServicio;

    @Autowired
    private UsuarioServicio userServicio;

    @GetMapping("")
    public String registro() {
        return "registro-form";
    }

    @PostMapping("")
    public String registroSave(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String dni) throws WebException {
        try {
            userSerServicio.save(username, password, password2, dni);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("username", username);
            return "registro-form";
        }
    }
}
