package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.UsuarioSeguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioSeguridadRepositorio extends JpaRepository<UsuarioSeguridad, String> {
    
}
