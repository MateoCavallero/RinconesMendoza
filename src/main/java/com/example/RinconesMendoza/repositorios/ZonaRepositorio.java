package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Zona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepositorio extends JpaRepository<Zona, String> {
    
    @Query("SELECT z FROM Zona z WHERE z.nombre LIKE :q")
    List<Zona> findAllByQ(@Param("q") String q);
    
}
