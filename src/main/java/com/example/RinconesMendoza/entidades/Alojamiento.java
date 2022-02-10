package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.TipoAlojamiento;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Alojamiento extends Locacion{
    private TipoAlojamiento tipoAlojamiento;
    private Boolean aireAcondicionado;
    private Boolean estacionamiento;
    private Boolean wifi;
    private Boolean pileta;
    private Boolean desayuno;
    private Boolean bar_restaurant;
}
