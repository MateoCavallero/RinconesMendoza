package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Comentario;
import com.example.RinconesMendoza.entidades.Locacion;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.repositorios.LocacionRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocacionService {

    @Autowired
    private LocacionRepositorio locacionRepo;

    @Transactional
    public void crear(Locacion locacion) throws WebException {
        validacion(locacion);
        locacionRepo.save(locacion);
    }

    public List<Locacion> listar() {
        return locacionRepo.findAll();
    }

    public Optional<Locacion> buscarId(String id) {
        return locacionRepo.findById(id);
    }

    public List<Locacion> listAllByQ(String q) {
        return locacionRepo.findAllByQ("%" + q + "%");
    }

    @Transactional
    public void delete(Locacion locacion) {
        locacionRepo.delete(locacion);
    }

    @Transactional
    public void eliminarPorId(String id) {
        Optional<Locacion> optional = locacionRepo.findById(id);
        if (optional.isPresent()) {
            locacionRepo.delete(optional.get());
        }
    }

    @Transactional
    public void setEstrellas(String id) {
        Optional<Locacion> optional = locacionRepo.findById(id);
        Integer i = 0;
        Integer suma = 0;

        if (optional.isPresent()) {
            
            Locacion locacion = optional.get();
            for (Comentario aux : locacion.getComentario()) {
                suma += aux.getPuntuacion();
                i++;
            }
            
            locacion.setEstrellas((double)suma/i);

        }

    }

    private void validacion(Locacion locacion) throws WebException {
        if (locacion.getNombre() == null || locacion.getNombre().length() < 3) {
            throw new WebException("El nombre no puede ser nulo o menor a 3 caracteres");
        }
        if (locacion.getDomicilio() == null || locacion.getDomicilio().length() < 3) {
            throw new WebException("");
        }
        if (locacion.getTelefono() == null || locacion.getTelefono().length() < 3) {
            throw new WebException("");
        }

    }

}
