package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Usuario;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.repositorios.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearUsuario(Usuario usuario) throws WebException {
        validacion(usuario);
        usuarioRepositorio.save(usuario);
    }

    public List<Usuario> listAll() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> findAllByQ(String id) {
        return usuarioRepositorio.findById(id);
    }

    public List<Usuario> listAllByQ(String q) {
        return usuarioRepositorio.findAllByQ("%" + q + "%");
    }

    @Transactional
    public void delete(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Usuario> optional = usuarioRepositorio.findById(id);
        if (optional.isPresent()) {
            usuarioRepositorio.delete(optional.get());
        }
    }

    private void validacion(Usuario usuario) throws WebException {
        if (usuario.getNombre() == null || usuario.getNombre().length() < 3) {
            throw new WebException("El nombre no puede ser nulo o menor a 3 caracteres");
        }
        if (usuario.getApellido() == null || usuario.getApellido().length() < 3) {
            throw new WebException("El apellido no puede ser nulo o menor a 3 caracteres");
        }
        if (usuario.getDni() == null || usuario.getDni() < 7) {
            throw new WebException("El DNI no puede ser nulo o menor a 7 caracteres y debe contener solo numeros");
        }
        if (usuario.getEmail() == null || usuario.getEmail().length() < 3) {
            throw new WebException("El nombre no puede ser nulo o menor a 3 caracteres");
        }
    }

}
