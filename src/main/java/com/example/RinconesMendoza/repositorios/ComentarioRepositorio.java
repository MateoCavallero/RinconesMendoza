package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, String> {
    
}
