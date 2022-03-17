package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.servicios.MailServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mail")
public class MailControlador {

    @Autowired
    private MailServicio mailService;

    @GetMapping("")
    public String crearUsuario() {
        return "mail";
    }

    @PostMapping("/enviarmail")
    public String enviarMail(@RequestParam String destinatario, @RequestParam String asunto, @RequestParam String contenido) {
        mailService.enviarMail(destinatario, asunto, contenido);
        return "redirect:/mail";
    }
}
