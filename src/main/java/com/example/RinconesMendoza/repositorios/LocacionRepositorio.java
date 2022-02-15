
package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Locacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacionRepositorio extends JpaRepository<Locacion, String>  {
    
}
