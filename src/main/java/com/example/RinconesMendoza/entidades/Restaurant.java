package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.TipoComida;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Restaurant extends Locacion{
    private TipoComida tipoDeComida;
    private Boolean menuCeliaco;
}