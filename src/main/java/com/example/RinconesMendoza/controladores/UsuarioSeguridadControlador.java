package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.servicios.UsuarioSeguridadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario-seguridad")
public class UsuarioSeguridadControlador {

    @Autowired
    private UsuarioSeguridadServicio userSegService;

    @GetMapping("/list")
    public String listarUsuarioSeguridad(Model model, @RequestParam(required = false) String q) {
        if (q != null) {
//            model.addAttribute("usuarios", userSegService.listAllByQ(q));
            model.addAttribute("usuarioSeguridad", userSegService.listAll());
        } else {
            model.addAttribute("usuarioSeguridad", userSegService.listAll());
        }
        return "usuarioSeguridad-list";
    }

}
