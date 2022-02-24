package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Foto;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.repositorios.FotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {
    
    @Autowired
    private FotoRepositorio fotoRepositorio;
    
//    public Foto guardar(MultipartFile archivo) throws WebException{
//        if (archivo != null) {
//            Foto foto = new Foto();
//            foto.setMime(archivo.getContentType());
//            foto.setNombre(archivo.getName());
//            foto.setContenido(archivo.getBytes());
//            return fotoRepositorio.save(foto);
//        }else{
//            return null;
//        }
//    }
    
}
