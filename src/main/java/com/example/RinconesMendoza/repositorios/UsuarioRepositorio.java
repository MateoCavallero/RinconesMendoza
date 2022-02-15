
package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE :q")
    List<Usuario> findAllByQ(@Param("q") String q);
    
    
}
