package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Restaurant;
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
    public void crearResto(Restaurant resto) {
        restaurantRepo.save(resto);
    }
    
    public List<Restaurant> listarResto() {
        return restaurantRepo.findAll();
    }
    
    public Optional<Restaurant> listById(String id){
        return restaurantRepo.findById(id);
    }

    public List<Restaurant> listAllByQ(String q) {
        return restaurantRepo.findAllByQ("%" + q + "%");
    }

    @Transactional
    public void eliminarResto(String id) {
        Optional<Restaurant> optional = restaurantRepo.findById(id);

        if (optional.isPresent()) {
            Restaurant resto = optional.get();
            restaurantRepo.delete(resto);
        }
    }
}
