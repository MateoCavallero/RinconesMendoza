package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.RangoDePrecio;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @Enumerated(value = EnumType.STRING)
    private RangoDePrecio rangoDePrecios;
    @OneToMany
    private List<Comentario> comentario;
    private Double estrellas;
    private String foto;
    @ManyToOne
    private Zona zona;
}
