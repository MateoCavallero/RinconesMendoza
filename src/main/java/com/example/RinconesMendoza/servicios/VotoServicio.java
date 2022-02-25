
package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Voto;
import com.example.RinconesMendoza.repositorios.VotoRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoServicio {
    
    @Autowired
    private VotoRepositorio votoRepositorio;
    
    @Transactional
    public void crearResto(Voto voto) {
        votoRepositorio.save(voto);
    }

    public List<Voto> listarResto() {
        return votoRepositorio.findAll();
    }
    
    public Optional<Voto> listById(String id){
        return votoRepositorio.findById(id);
    }

    public List<Voto> listAllByQ(String q) {
        return votoRepositorio.findByLocacion(q);
    }
    
    

}
