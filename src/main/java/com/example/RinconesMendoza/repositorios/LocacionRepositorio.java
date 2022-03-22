
package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Locacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacionRepositorio extends JpaRepository<Locacion, String>  {
    
    @Query("SELECT l FROM Locacion l WHERE l.nombre LIKE :q")
    List<Locacion> findAllByQ(@Param("q") String q);
    
}
