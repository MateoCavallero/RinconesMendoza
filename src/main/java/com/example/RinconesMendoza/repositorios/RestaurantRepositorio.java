
package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepositorio extends JpaRepository<Restaurant, String>{
    
    @Query("SELECT l FROM Restaurant l WHERE l.zona.id = '17193838-a433-4b46-b977-9535e71a7dad'")
    List<Restaurant> findAllUco();

    @Query("SELECT l FROM Restaurant l WHERE l.zona.id = '4ab268bc-9522-490c-b08c-589ec137b327'")
    List<Restaurant> findAllGranMendoza();

    @Query("SELECT l FROM Restaurant l WHERE l.zona.id = '156c9d3f-5600-4631-84a4-b2d59f696456'")
    List<Restaurant> findAllZonaSur();

    @Query("SELECT l FROM Restaurant l WHERE l.zona.id = '69256f49-6d8f-4fdc-8afa-7166e194b545'")
    List<Restaurant> findAllZonaEste();

    @Query("SELECT l FROM Restaurant l WHERE l.zona.id = '66dc0dbc-bf96-4261-8442-f6b8ff468136'")
    List<Restaurant> findAllAltaMontana();
    
    @Query("SELECT l FROM Restaurant l WHERE l.nombre LIKE :q")
    List<Restaurant> findAllByQ(@Param("q") String q);
    
}
