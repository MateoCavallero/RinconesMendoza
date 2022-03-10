package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Alojamiento;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.servicios.AlojamientoServicio;
import com.example.RinconesMendoza.servicios.ComentarioServicio;
import com.example.RinconesMendoza.servicios.ZonaServicio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alojamiento")
public class AlojamientoControlador {

    @Autowired
    private AlojamientoServicio alojamientoServis;
    
    @Autowired
    private ComentarioServicio comentarioService;

    @Autowired
    private ZonaServicio zonaService;

    @GetMapping("/form")
    public String crearAlojaiento(Model model, Model modelz, @RequestParam(required = false) String id) {
        modelz.addAttribute("zonas", zonaService.listAll());
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
    public String saveUsuario(RedirectAttributes redirect, @ModelAttribute Alojamiento alojamiento, @RequestParam("file") MultipartFile imagen) {
        try {
            try {
                if (!imagen.isEmpty()) {
                    Path directorioImagenes = Paths.get(".//src/main/resources/static/images/locacion/");
                    String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
                    byte[] bytesImg = imagen.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
                    Files.write(rutaCompleta, bytesImg);
                    alojamiento.setFoto(imagen.getOriginalFilename());
                }
            } catch (IOException e) {
                System.out.println(e);
            }
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String eliminarAlojamiento(@RequestParam(required = true) String id) {
        alojamientoServis.deletefinById(id);
        return "redirect:/alojamiento/list";
    }

    @GetMapping("/alojamiento")
    public String vistaAlojamiento(Model model, Model modelcomentario, @RequestParam (required = true)  String id) {
        Optional<Alojamiento> optional = alojamientoServis.findById(id);
        model.addAttribute("alojamiento", optional.get());
        
        modelcomentario.addAttribute("comentarios", comentarioService.listLocacion(id));
        
        return "alojamientos";
    }
}
