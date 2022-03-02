
package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Voto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepositorio extends JpaRepository<Voto, String> {
    
    @Query("SELECT c FROM Voto c WHERE c.id = :id")
    List<Voto> findByLocacion(@Param("id")String id);
}
