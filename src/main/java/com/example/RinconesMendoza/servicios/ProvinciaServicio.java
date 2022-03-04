package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Locacion;
import com.example.RinconesMendoza.repositorios.LocacionRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServicio {
        
    @Autowired
    private LocacionRepositorio locacionRepo;
    
    public List<Locacion> listAll() {
        return locacionRepo.findAll();
    }
}
