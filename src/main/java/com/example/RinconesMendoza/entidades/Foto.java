package com.example.RinconesMendoza.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Foto {
    @Id
    private String id;
    private String nombre;
    private String mime;
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;
}
