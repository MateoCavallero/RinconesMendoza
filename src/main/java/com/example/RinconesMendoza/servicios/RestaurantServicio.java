package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Restaurant;
import com.example.RinconesMendoza.excepciones.WebException;
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
    public void crearRestaurant(Restaurant restaurant) throws WebException {
        validacion(restaurant);
        System.out.println(restaurant.getRangoDePrecios());
        restaurantRepo.save(restaurant);
    }

    public List<Restaurant> listAll() {
        return restaurantRepo.findAll();
    }

    public List<Restaurant> findAllByQ(String q) {
        return restaurantRepo.findAllByQ("%" + q + "%");
    }

    public Optional<Restaurant> findById(String id) {
        return restaurantRepo.findById(id);
    }

    public List<Restaurant> listAllUco() {
        return restaurantRepo.findAllUco();
    }
    
    public List<Restaurant> listAllGranMendoza() {
        return restaurantRepo.findAllGranMendoza();
    }
    
    public List<Restaurant> listAllZonaEste() {
        return restaurantRepo.findAllZonaEste();
    }
    
    public List<Restaurant> listAllZonaSur() {
        return restaurantRepo.findAllZonaSur();
    }
    
    public List<Restaurant> listAllMontana() {
        return restaurantRepo.findAllAltaMontana();
    }

    @Transactional
    public void deletefinById(String id) {
        Optional<Restaurant> optional = restaurantRepo.findById(id);

        if (optional.isPresent()) {
            Restaurant restaurant = optional.get();
            restaurantRepo.delete(restaurant);
        }
    }

    private void validacion(Restaurant restaurant) throws WebException {
        if (restaurant.getNombre() == null || restaurant.getNombre().isEmpty()) {
            throw new WebException("El nombre no puede ser nulo");
        }
        if (restaurant.getDomicilio() == null || restaurant.getDomicilio().isEmpty()) {
            throw new WebException("El domicilio no puede ser vacío");
        }
        if (restaurant.getTelefono() == null || restaurant.getTelefono().isEmpty() || restaurant.getTelefono().length() < 10) {
            throw new WebException("El Telefono no puede ser nulo o menor a 10 caracteres y debe contener solo numeros");
        }
        if (restaurant.getWeb() == null || restaurant.getWeb().isEmpty()) {
            throw new WebException("La direccion web no puede estar vacía");
        }

    }
}
