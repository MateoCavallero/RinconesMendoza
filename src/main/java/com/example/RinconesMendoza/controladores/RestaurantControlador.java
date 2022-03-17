package com.example.RinconesMendoza.controladores;
import com.example.RinconesMendoza.entidades.Restaurant;
import com.example.RinconesMendoza.servicios.ComentarioServicio;
import com.example.RinconesMendoza.servicios.RestaurantServicio;
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
@RequestMapping("/restaurant")
public class RestaurantControlador {

    @Autowired
    private RestaurantServicio restoService;
    
     @Autowired
    private ComentarioServicio comentarioService;

    @Autowired
    private ZonaServicio zonaService;
    
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/form")
    public String crearRestaurant(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Restaurant> optional = restoService.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("restaurant", optional.get());
            } else {
                return "redirect:/restaurant/list";
            }
        } else {
            model.addAttribute("restaurant", new Restaurant());
        }
        return "restaurant-form";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping("/save")
    public String saveUsuario(RedirectAttributes redirect, @ModelAttribute Restaurant restaurant, @RequestParam("file") MultipartFile imagen) {
        try {
            try {
                if (!imagen.isEmpty()) {
                    Path directorioImagenes = Paths.get(".//src/main/resources/images/locacion/");
                    String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
                    byte[] bytesImg = imagen.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
                    Files.write(rutaCompleta, bytesImg);
                    restaurant.setFoto(imagen.getOriginalFilename());
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            restoService.crearRestaurant(restaurant);
            redirect.addFlashAttribute("success", "Restaurant guardado con exito");
            return "redirect:/restaurant/list";
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/restaurant/list";
        }
    }
    
    @GetMapping("/list")
    public String listRestaurant(Model model) {
        model.addAttribute("restaurant", restoService.listAll());
        return "restaurant-list";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String deleteRestaurant(@RequestParam(required = true) String id) {
        restoService.deletefinById(id);
        return "redirect:/restaurant/list";
    }
    
    @GetMapping("/alojamiento")
    public String vistaAlojamiento(Model model, Model modelcomentario, @RequestParam(required = true) String id) {
        Optional<Restaurant> optional = restoService.findById(id);
        model.addAttribute("restaurant", optional.get());
        modelcomentario.addAttribute("comentarios", comentarioService.listLocacion(id));
        return "alojamientos";
    }
}
