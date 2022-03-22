package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Alojamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlojamientoRepositorio extends JpaRepository<Alojamiento, String> {

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '17193838-a433-4b46-b977-9535e71a7dad'")
    List<Alojamiento> findAllUco();

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '4ab268bc-9522-490c-b08c-589ec137b327'")
    List<Alojamiento> findAllGranMendoza();

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '156c9d3f-5600-4631-84a4-b2d59f696456'")
    List<Alojamiento> findAllZonaSur();

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '69256f49-6d8f-4fdc-8afa-7166e194b545'")
    List<Alojamiento> findAllZonaEste();

    @Query("SELECT l FROM Alojamiento l WHERE l.zona.id = '66dc0dbc-bf96-4261-8442-f6b8ff468136'")
    List<Alojamiento> findAllAltaMontana();

    @Query("SELECT l FROM Alojamiento l WHERE l.nombre LIKE :q OR l.zona.nombre LIKE :q")
    List<Alojamiento> findAllByQ(@Param("q") String q);

}
