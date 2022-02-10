package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.TipoActividad;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Actividades extends Locacion{

    private TipoActividad tipoDeActividad;
    private Boolean aptoParaNiños;
    private String duracionAprox;
    private Boolean comidaEnElLugar;
}
