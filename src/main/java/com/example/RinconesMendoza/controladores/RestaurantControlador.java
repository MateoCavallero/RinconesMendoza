package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Restaurant;
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
            Optional<Restaurant> optional = restoService.listById(id);
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

    @GetMapping("/list")
    public String listRestaurant(Model model, @RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("restaurant", restoService.listAllByQ(q));
        } else {
            model.addAttribute("restaurant", restoService.listarResto());
        }
        return "restaurant-list";
    }
    
    @GetMapping("/delete")
    public String deleteRestaurant (@RequestParam(required = true)String id){
        restoService.eliminarResto(id);
        return "redirect:/restaurant/list";        
    }

}
