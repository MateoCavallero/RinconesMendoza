
package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Alojamiento;
import com.example.RinconesMendoza.servicios.AlojamientoServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlojamientoControlador {
    
    @Autowired
    private AlojamientoServicio alojamientoServis;
    
   // @GetMapping("/list")
   // public String listarAlojamientos(Model model){
        
    //}
    @GetMapping("/form")
    public String crearAlojaiento(Model model){
        model.addAttribute("alojamiento", new Alojamiento());
        return "alojamiento-form";
    }
}
