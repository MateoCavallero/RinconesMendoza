package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Zona;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.repositorios.ZonaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaServicio {

    @Autowired
    private ZonaRepositorio zonaRepositorio;

    @Transactional
    public void crearZona(Zona zona) throws WebException {
        validacion(zona);
        zonaRepositorio.save(zona);
    }

    public List<Zona> listAll() {
        return zonaRepositorio.findAll();
    }

    public Optional<Zona> findAllByQ(String id) {
        return zonaRepositorio.findById(id);
    }

    public List<Zona> listAllByQ(String q) {
        return zonaRepositorio.findAllByQ("%" + q + "%");
    }

    @Transactional
    public void delete(Zona zona) {
        zonaRepositorio.delete(zona);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Zona> optional = zonaRepositorio.findById(id);
        if (optional.isPresent()) {
            zonaRepositorio.delete(optional.get());
        }
    }

    private void validacion(Zona zona) throws WebException {
        if (zona.getNombre() == null || zona.getNombre().length() < 3) {
            throw new WebException("El nombre no puede ser nulo o menor a 3 caracteres");
        }
        if (zona.getProv() == null) {
            throw new WebException("El apellido no puede ser nulo o menor a 3 caracteres");
        }
    }
}