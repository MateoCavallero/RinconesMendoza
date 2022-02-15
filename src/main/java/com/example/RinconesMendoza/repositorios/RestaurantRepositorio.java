
package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Restaurantes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepositorio extends JpaRepository<Restaurantes, String>{
    
    @Query("SELECT l FROM Restaurantes l WHERE l.nombre LIKE :q")
    List<Restaurantes> findAllByQ(@Param("q") String q);
    
}
