package com.example.RinconesMendoza.entidades;

import com.example.RinconesMendoza.utils.Role;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Entity
@Data
public class UsuarioSeguridad extends Usuario{
    
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role rol;
    
}
