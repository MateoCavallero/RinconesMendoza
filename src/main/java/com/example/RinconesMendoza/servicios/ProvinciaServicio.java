package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Provincia;
import com.example.RinconesMendoza.repositorios.ProvinciaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServicio {
        
    @Autowired
    private ProvinciaRepositorio provinciaRepo;
    
    public List<Provincia> listAll() {
        return provinciaRepo.findAll();
    }
}
