package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Alojamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlojamientoRepositorio extends JpaRepository<Alojamiento, String> {

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '6796dc3c-70fe-4726-b289-bd40a8672422'")
    List<Alojamiento> findAllUco();

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '5621682c-a80f-4ec8-a3e7-04b80bca4e35'")
    List<Alojamiento> findAllGranMendoza();

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '5496c668-1f96-40a9-927b-b1dc9c279da2'")
    List<Alojamiento> findAllZonaSur();

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '728c9365-980a-4ab7-b854-fec06efb1be6'")
    List<Alojamiento> findAllZonaEste();

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '63a3a764-4e26-4d08-8f92-58e34f0092cc'")
    List<Alojamiento> findAllAltaMontana();

    @Query("SELECT l FROM Alojamiento l WHERE l.nombre LIKE :q")
    List<Alojamiento> findAllByQ(@Param("q") String q);

}
