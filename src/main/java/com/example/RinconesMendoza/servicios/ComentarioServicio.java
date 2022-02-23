package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Comentario;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.repositorios.ComentarioRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Transactional
    public void crearComentario(Comentario comentario) throws WebException {
        validacion(comentario);
        comentarioRepositorio.save(comentario);
    }

    public List<Comentario> listAll() {
        return comentarioRepositorio.findAll();
    }

    public Optional<Comentario> findAllByQ(String id) {
        return comentarioRepositorio.findById(id);
    }

    public List<Comentario> listAllByQ(String q) {
        return comentarioRepositorio.findAllByQ("%"+q+"%");
    }

    @Transactional
    public void delete(Comentario comentario) {
        comentarioRepositorio.delete(comentario);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Comentario> optional = comentarioRepositorio.findById(id);
        if (optional.isPresent()) {
            comentarioRepositorio.delete(optional.get());
        }
    }

    private void validacion(Comentario comentario) throws WebException {
        if (comentario.getLocacion() == null){
            throw new WebException("Debe seleccionar algun lugar para comentar");
        }
        if (comentario.getOpinion() == null || comentario.getOpinion().length() < 30) {
            throw new WebException("La opinion no puede ser nula o menor a 30 caracteres");
        }
        if (comentario.getPuntuacion() == null) {
            throw new WebException("Debe seleccionar alguna opción de puntación");
        }
    }

}
