package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.TipoComida;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Entity
@Data
public class Restaurant extends Locacion{
    @Enumerated(value = EnumType.STRING)
    private TipoComida tipoDeComida;
    private Boolean menuCeliaco;
    private String foto;
}
