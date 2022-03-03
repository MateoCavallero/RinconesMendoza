package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Zona;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.servicios.ProvinciaServicio;
import com.example.RinconesMendoza.servicios.ZonaServicio;
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
@RequestMapping("/zona")
public class ZonaControlador {

    @Autowired
    private ZonaServicio zonaService;
    
    @Autowired
    private ProvinciaServicio provinciaService;

    @GetMapping("/form")
    public String crearZona(Model model, Model modelP, @RequestParam(required = false) String id) {
        modelP.addAttribute("provincias", provinciaService.listAll());
        if (id != null) {
            Optional<Zona> optional = zonaService.findAllByQ(id);
            if (optional.isPresent()) {
                model.addAttribute("zona", optional.get());
            } else {
                return "redirect:/zona/list";
            }
        } else {
            model.addAttribute("zona", new Zona());
        }
        return "zona-form";
    }

    @PostMapping("/save")
    public String saveZona(RedirectAttributes redirect, @ModelAttribute Zona zona) {
        try {
            zonaService.crearZona(zona);
            redirect.addFlashAttribute("success", "Zona guardada con exito");
            return "redirect:/zona/list";
        } catch (WebException e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/zona/list";
        }
    }

    @GetMapping("/list")
    public String listZona(Model model, @RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("zona", zonaService.listAllByQ(q));
        } else {
            model.addAttribute("zona", zonaService.listAll());
        }
        return "zona-list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String eliminarZona(@RequestParam(required = true) String id) {
        zonaService.deleteById(id);
        return "redirect:/zona/list";
    }

    @GetMapping("/zona1")
    public String zona1() {
        return "zona-1";
    }

    @GetMapping("/zona2")
    public String zona2() {
        return "zona-2";
    }

    @GetMapping("/zona3")
    public String zona3() {
        return "zona-3";
    }

    @GetMapping("/zona4")
    public String zona4() {
        return "zona-4";
    }

    @GetMapping("/zona5")
    public String zona5() {
        return "zona-5";
    }

}
