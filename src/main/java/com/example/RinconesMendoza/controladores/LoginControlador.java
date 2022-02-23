package com.example.RinconesMendoza.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginControlador {

    @GetMapping("")
    public String login(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String username, @RequestParam(required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "El usuario ingresado o la contraseña son incorrecto");
            if (username != null) {
                model.addAttribute("username", username);
            }
        }

        return "login";
    }
}
