package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Restaurantes;
import com.example.RinconesMendoza.repositorios.RestaurantRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantServicio {

    @Autowired
    private RestaurantRepositorio restaurantRepo;

    @Transactional
    public void crearResto(Restaurantes resto) {
        restaurantRepo.save(resto);
    }

    public List<Restaurantes> listarResto(Restaurantes resto) {
        return restaurantRepo.findAll();
    }
    
    public Optional<Restaurantes> listById(String id){
        return restaurantRepo.findById(id);
    }

    public List<Restaurantes> listAllByQ(String q) {
        return restaurantRepo.findAllByQ("%" + q + "%");
    }

    @Transactional
    public void eliminarResto(String id) {
        Optional<Restaurantes> optional = restaurantRepo.findById(id);

        if (optional.isPresent()) {
            Restaurantes resto = optional.get();
            restaurantRepo.delete(resto);
        }
    }
}
