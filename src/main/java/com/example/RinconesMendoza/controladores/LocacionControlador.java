package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.servicios.LocacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/locacion")
public class LocacionControlador {
    
    @Autowired
    private LocacionService locacionService;
    

}
