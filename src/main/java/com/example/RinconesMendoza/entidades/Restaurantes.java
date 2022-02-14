package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.Calidad;
import com.example.RinconesMendoza.utils.TipoComida;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Restaurantes extends Locacion{
    private TipoComida tipoDeComida;
    private Calidad calidadServicio;
    private Boolean menuCeliaco;
}
