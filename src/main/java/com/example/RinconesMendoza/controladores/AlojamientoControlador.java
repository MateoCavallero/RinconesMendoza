package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Alojamiento;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.servicios.AlojamientoServicio;
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
@RequestMapping("/alojamiento")
public class AlojamientoControlador {

    @Autowired
    private AlojamientoServicio alojamientoServis;

    @GetMapping("/form")
    public String crearAlojaiento(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Alojamiento> optional = alojamientoServis.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("alojamiento", optional.get());
            } else {
                return "redirect:/alojamiento/list";
            }
        } else {
            model.addAttribute("alojamiento", new Alojamiento());
        }
        return "alojamiento-form";

    }

    @PostMapping("/save")
    public String saveUsuario(RedirectAttributes redirect, @ModelAttribute Alojamiento alojamiento) {
        try {
            alojamientoServis.crearAlojamiento(alojamiento);
            redirect.addFlashAttribute("success", "Alojamiento fue guardado con exito");
            return "redirect:/alojamiento/list";
        } catch (WebException e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/alojamiento/list";
        }
    }

    @GetMapping("/list")
    public String listAlojamiento(Model model) {
        model.addAttribute("alojamiento", alojamientoServis.listAll());
        return "alojamiento-list";
    }

    @GetMapping("/delete")
    public String eliminarAlojamiento(@RequestParam(required = true) String id) {
        alojamientoServis.deletefinById(id);
        return "redirect:/usuario/list";
    }
}
