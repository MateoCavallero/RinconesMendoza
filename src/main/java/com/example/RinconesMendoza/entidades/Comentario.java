package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.Calidad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Comentario {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Locacion locacion;
    private String opinion;
    private Calidad puntuacion;
    
    
    
}
