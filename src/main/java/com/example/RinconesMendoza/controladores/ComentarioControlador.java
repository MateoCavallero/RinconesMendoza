package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Alojamiento;
import com.example.RinconesMendoza.entidades.Comentario;
import com.example.RinconesMendoza.entidades.Locacion;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.servicios.ComentarioServicio;
import com.example.RinconesMendoza.servicios.LocacionService;
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
@RequestMapping("/comentario")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioService;
    @Autowired
    private LocacionService locacionService; 

    @GetMapping("/form")
    public String crearComentario(Model model, Model l,@RequestParam(required = true) String id) {
            Comentario com = new Comentario();
            Optional<Locacion> optional = locacionService.buscarId(id);
            com.setLocacion(optional.get());
            model.addAttribute("comentario", com);
        return "comentario-form";
    }

    @PostMapping("/save")
    public String saveComentario(RedirectAttributes redirect, @ModelAttribute Comentario comentario) {
        
        try {
           comentarioService.crearComentario(comentario);            
            redirect.addFlashAttribute("success", "Comentario guardado con exito");
            return "redirect:/alojamiento/alojamiento?id="+comentario.getLocacion().getId();
        } catch (WebException e) {
            System.out.println(e.getMessage());
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/comentario/list";
        }
    }

    @GetMapping("/list")
    public String listComentario(Model model, @RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("comentario", comentarioService.listAllByQ(q));
        } else {
            model.addAttribute("comentario", comentarioService.listAll());
        }
        return "comentario-list";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String eliminarComentario(@RequestParam(required = true) String id) {
        comentarioService.deleteById(id);
        return "redirect:/comentario/list";
    }

}
