package com.example.RinconesMendoza.entidades;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class UsuarioSeguridad extends Usuario{
    
    private String username;
    private String password;
    
}
