package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.UsuarioSeguridad;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.repositorios.UsuarioSeguridadRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSeguridadServicio {

    @Autowired
    private UsuarioSeguridadRepositorio userSegRepositorio;

    @Transactional
    public UsuarioSeguridad save(String username, String password, String password2) throws WebException {
        UsuarioSeguridad user = new UsuarioSeguridad();

        if (username.isEmpty() || username == null) {
            throw new WebException("El nombre de usuario no puede estar vacio");
        }
        if (password == null || password2 == null || password.isEmpty() || password2.isEmpty() ) {
            throw new WebException("La contraseña no puede estar vacia");
        }
        if (!password.equals(password2)) {
            throw new WebException("Las contraseñas deben ser iguales");
        }
        
        user.setUsername(username);
        user.setPassword(password);
        
        
        return userSegRepositorio.save(user);
    }
}
