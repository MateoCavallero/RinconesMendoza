package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.TipoAlojamiento;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Entity
@Data
public class Alojamiento extends Locacion{
    @Enumerated(value = EnumType.STRING)
    private TipoAlojamiento tipoAlojamiento;
    
    private Boolean aireAcondicionado;
    private Boolean estacionamiento;
    private Boolean wifi;
    private Boolean pileta;
    private Boolean desayuno;
    private Boolean bar_restaurant;
}
