package com.example.RinconesMendoza.repositorios;

import com.example.RinconesMendoza.entidades.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE :q")
    List<Usuario> findAllByQ(@Param("q") String q);

    @Query("SELECT u FROM Usuario u WHERE u.dni LIKE :dni")
    Usuario findAllByDNI(@Param("dni") String dni);

    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Optional<Usuario> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Usuario findByName(@Param("username") String username);

}
