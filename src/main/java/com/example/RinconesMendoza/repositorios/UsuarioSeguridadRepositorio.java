package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.UsuarioSeguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioSeguridadRepositorio extends JpaRepository<UsuarioSeguridad, String> {
    
    @Query("SELECT u FROM UsuarioSeguridad u WHERE u.username = :username")
    UsuarioSeguridad findByUsername(@Param("username") String username);
}
