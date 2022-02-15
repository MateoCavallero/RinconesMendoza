package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, String> {
    
    @Query("SELECT c FROM Comentario c WHERE c.nombre LIKE :q")
    List<Comentario> findAllByQ(@Param("q") String q);
    
}
