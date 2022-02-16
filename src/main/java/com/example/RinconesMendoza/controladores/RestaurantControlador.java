package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Restaurantes;
import com.example.RinconesMendoza.servicios.RestaurantServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/restaurant")
public class RestaurantControlador {
    
    @Autowired
    private RestaurantServicio restoService;
    
    @GetMapping("/form")
    public String crearRestaurant(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Restaurantes> optional = restoService.listById(id);
            if (optional.isPresent()) {
                model.addAttribute("comentario", optional.get());
            } else {
                return "redirect:/restaurant/list";
            }
        } else {
            model.addAttribute("restaurant", new Restaurantes());
        }
        return "comentario-form";
    }

}
