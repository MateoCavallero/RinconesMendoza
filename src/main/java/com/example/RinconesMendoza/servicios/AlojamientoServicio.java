
package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Alojamiento;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.repositorios.AlojamientoRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlojamientoServicio {
    
    @Autowired
    private AlojamientoRepositorio alojamientoRepo;
    
    @Transactional
    public void crearAlojamiento(Alojamiento alojamiento) throws WebException{
        validacion(alojamiento);
        alojamientoRepo.save(alojamiento);
    }
    
    public List<Alojamiento> listAll(){
        return alojamientoRepo.findAll();
    }
    
    public Optional<Alojamiento> findById(String id){
        return alojamientoRepo.findById(id);
    }
    
    @Transactional
    public void deletefinById(String id){
         Optional<Alojamiento> optional = alojamientoRepo.findById(id);
        
        if (optional.isPresent()) {
            Alojamiento alojamiento = optional.get();
            alojamientoRepo.delete(alojamiento);
        }
    }
    
    private void validacion(Alojamiento alojamiento) throws WebException {
        if (alojamiento.getNombre() == null || alojamiento.getNombre().isEmpty()) {
            throw new WebException("El nombre no puede ser nulo");
        }
        if (alojamiento.getDomicilio() == null || alojamiento.getDomicilio().isEmpty()) {
            throw new WebException("El domicilio no puede ser vacío");
        }
        if (alojamiento.getTelefono() == null ||alojamiento.getTelefono().isEmpty() ||alojamiento.getTelefono().length() < 10) {
            throw new WebException("El Telefono no puede ser nulo o menor a 10 caracteres y debe contener solo numeros");
        }
        if (alojamiento.getWeb() == null || alojamiento.getWeb().isEmpty()) {
            throw new WebException("La direccion web no puede estar vacía");
        }
       
    }
}
