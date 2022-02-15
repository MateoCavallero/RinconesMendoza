
package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Alojamiento;
import com.example.RinconesMendoza.repositorios.AlojamientoRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlojamientoServicio {
    
    @Autowired
    private AlojamientoRepositorio alojamientoRepo;
    
    @Transactional
    public void crearAlojamiento(Alojamiento alojamiento){
        alojamientoRepo.save(alojamiento);
    }
    
    public List<Alojamiento> listAll(Alojamiento alojamiento){
        return alojamientoRepo.findAll();
    }
    
    public Optional<Alojamiento> findById(String id){
        return alojamientoRepo.findById(id);
    }
    
    @Transactional
    public void deletefyId(String id){
         Optional<Alojamiento> optional = alojamientoRepo.findById(id);
        
        if (optional.isPresent()) {
            Alojamiento alojamiento = optional.get();
            alojamientoRepo.delete(alojamiento);
        }
    }
}
