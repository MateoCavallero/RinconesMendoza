
package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Locacion;
import com.example.RinconesMendoza.repositorios.LocacionRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocacionServis {
    
    @Autowired
    private LocacionRepositorio locacionRepo;
    
    @Transactional
    public void crear(Locacion locacion){
        locacionRepo.save(locacion);
    }
    
    public List<Locacion> listar(Locacion locacion){
        return locacionRepo.findAll();
    }
    
    public Optional<Locacion> buscarId(String id){
       return locacionRepo.findById(id);
    }
}
