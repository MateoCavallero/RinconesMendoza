package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepositorio extends JpaRepository<Comentario, String> {
    
}
