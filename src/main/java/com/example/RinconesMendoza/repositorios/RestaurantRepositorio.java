
package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepositorio extends JpaRepository<Restaurant, String>{
    
    @Query("SELECT l FROM Restaurant l WHERE l.nombre LIKE :q")
    List<Restaurant> findAllByQ(@Param("q") String q);
    
}
