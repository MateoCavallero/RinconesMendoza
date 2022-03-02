package com.example.RinconesMendoza.servicios;

import com.example.RinconesMendoza.entidades.Usuario;
import com.example.RinconesMendoza.entidades.UsuarioSeguridad;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.repositorios.UsuarioSeguridadRepositorio;
import com.example.RinconesMendoza.utils.Role;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSeguridadServicio implements UserDetailsService{

    @Autowired
    private UsuarioServicio usuarioService;

    @Autowired
    private UsuarioSeguridadRepositorio userSegRepositorio;

    @Transactional
    public UsuarioSeguridad save(String username, String password, String password2, String dni) throws WebException {
        UsuarioSeguridad user = new UsuarioSeguridad();

        if (dni == null || dni.isEmpty()) {
            throw new WebException("El dni no puede estar vacio");
        }
        
        Usuario usuario = usuarioService.findByDNI(dni);
        
        if (usuario == null) {
            throw new WebException("No se puede registrar un usuario con un DNI que no exista en la base de datos");
        }
        if (username == null || username.isEmpty()) {
            throw new WebException("El nombre de usuario no puede estar vacio");
        }
        if (findByUsername(username) != null) {
            throw new WebException("El nombre de usuario ya esta registrado intente con otro");
        }
        if (password == null || password2 == null || password.isEmpty() || password2.isEmpty()) {
            throw new WebException("La contraseña no puede estar vacia");
        }
        if (!password.equals(password2)) {
            throw new WebException("Las contraseñas deben ser iguales");
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setId(usuario.getId());
        user.setNombre(usuario.getNombre());
        user.setApellido(usuario.getApellido());
        user.setDni(dni);
        user.setEmail(usuario.getEmail());
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRol(Role.USER);
        usuarioService.delete(usuario);
        
        return userSegRepositorio.save(user);
    }
    
    
    public UsuarioSeguridad findByUsername(String username) {
        return userSegRepositorio.findByUsername(username);
    }
    
        public List<UsuarioSeguridad> listAll() {
        return userSegRepositorio.findAll();
    }

    public List<UsuarioSeguridad> findAllByQ(String q) {
//        return userSegRepositorio.findAllByQ("%" + q + "%");
        return userSegRepositorio.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UsuarioSeguridad userSeg = userSegRepositorio.findByUsername(username);
            User user;
            
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userSeg.getRol()));
            
            return new User(username, userSeg.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario solicitado no existe");
        }
        
        
    }
}
