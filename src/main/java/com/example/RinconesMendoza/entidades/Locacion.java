package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.RangoDePrecio;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public abstract class Locacion {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String web;
<<<<<<< HEAD
    private String rangoDePrecios;
=======
    
    @Enumerated(value = EnumType.STRING)
    private RangoDePrecio rangoDePrecios;
    
>>>>>>> 22c26c4f2ac5e930157166171932232c105c03e7
    private Double estrellas;
    
    @OneToOne
    private Foto foto;
    @ManyToOne
    private Zona zona;
}
