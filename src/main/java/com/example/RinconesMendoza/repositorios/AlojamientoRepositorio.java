
package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Alojamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlojamientoRepositorio extends JpaRepository<Alojamiento, String> {
    
}
