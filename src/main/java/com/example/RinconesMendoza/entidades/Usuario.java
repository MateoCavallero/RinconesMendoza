package com.example.RinconesMendoza.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    
    
}
