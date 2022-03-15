package com.example.RinconesMendoza.servicios;
import com.example.RinconesMendoza.entidades.Usuario;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.repositorios.UsuarioRepositorio;
import com.example.RinconesMendoza.utils.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class UsuarioServicio implements UserDetailsService{
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    public Usuario save(Usuario usuario, String password2) throws WebException {
        Usuario user = new Usuario();
        Usuario usuario2 = new Usuario();
        if (usuario.getDni() == null || usuario.getDni().isEmpty()) {
            throw new WebException("El dni no puede estar vacio");
        }
        usuario2 = findByDNI(usuario.getDni());
        if (usuario == null) {
            throw new WebException("No se puede registrar un usuario con un DNI que no exista en la base de datos");
        }
        if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            throw new WebException("El nombre de usuario no puede estar vacio");
        }
        if (findByUsername(usuario.getUsername()) != null) {
            throw new WebException("El nombre de usuario ya esta registrado intente con otro");
        }
        if (usuario.getPassword() == null || password2 == null || usuario.getPassword().isEmpty() || password2.isEmpty()) {
            throw new WebException("La contraseña no puede estar vacia");
        }
        if (!usuario.getPassword().equals(password2)) {
            throw new WebException("Las contraseñas deben ser iguales");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setId(usuario.getId());
        user.setNombre(usuario.getNombre());
        user.setApellido(usuario.getApellido());
        user.setDni(usuario.getDni());
        user.setEmail(usuario.getEmail());
        user.setUsername(usuario.getUsername());
        user.setPassword(encoder.encode(usuario.getPassword()));
        user.setFoto(usuario.getFoto());
        user.setRol(Role.USER);
//        delete(usuario2);
        return usuarioRepositorio.save(user);
    }
    public Usuario findByUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
    }
    public List<Usuario> listAll() {
        return usuarioRepositorio.findAll();
    }

<<<<<<< HEAD

=======
    
>>>>>>> 290d6b32dbd84f284344e6cba3a11c48cdbb8a2a
    public Optional<Usuario> findAllByQ(String id) {
        return usuarioRepositorio.findById(id);
    }
    public Usuario findByDNI(String dni) {
        return usuarioRepositorio.findAllByDNI(dni);
    }
    public List<Usuario> listAllByQ(String q) {
        return usuarioRepositorio.findAllByQ("%" + q + "%");
    }
    @Transactional
    public void delete(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }
    @Transactional
    public void deleteById(String id) {
        Optional<Usuario> optional = usuarioRepositorio.findById(id);
        if (optional.isPresent()) {
            usuarioRepositorio.delete(optional.get());
        }
    }
    private void validacion(Usuario usuario) throws WebException {
        if (findByDNI(usuario.getDni()) != null) {
            throw new WebException("El DNI ya existe no se puede registrar con el mismo DNI");
        }
        if (usuario.getNombre() == null || usuario.getNombre().length() < 3) {
            throw new WebException("El nombre no puede ser nulo o menor a 3 caracteres");
        }
        if (usuario.getApellido() == null || usuario.getApellido().length() < 3) {
            throw new WebException("El apellido no puede ser nulo o menor a 3 caracteres");
        }
        if (usuario.getDni() == null || usuario.getDni().length() < 7) {
            throw new WebException("El DNI no puede ser nulo o menor a 7 caracteres y debe contener solo numeros");
        }
        if (usuario.getEmail() == null || usuario.getEmail().length() < 3) {
            throw new WebException("El nombre no puede ser nulo o menor a 3 caracteres");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario user0 = usuarioRepositorio.findByUsername(username);
            User user;
            
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+user0.getRol()));
<<<<<<< HEAD

            ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
            HttpSession sesion = attr.getRequest().getSession(true);
            sesion.setAttribute("usuariosesion", user0);

=======
            
            ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
            HttpSession sesion = attr.getRequest().getSession(true);
            sesion.setAttribute("usuariosesion", user0);
            
>>>>>>> 290d6b32dbd84f284344e6cba3a11c48cdbb8a2a
            return new User(username, user0.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario solicitado no existe");
        }
    }
    
}