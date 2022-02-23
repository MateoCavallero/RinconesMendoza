
package com.example.RinconesMendoza.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/actividades")
public class ActividadesControlador {
    
    @GetMapping("")
    public String login() {
        return "actividades";
    }
}
