
package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
}
