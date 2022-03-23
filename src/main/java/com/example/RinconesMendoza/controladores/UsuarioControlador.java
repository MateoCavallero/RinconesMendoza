package com.example.RinconesMendoza.controladores;

import com.example.RinconesMendoza.entidades.Usuario;
import com.example.RinconesMendoza.excepciones.WebException;
import com.example.RinconesMendoza.servicios.MailServicio;
import com.example.RinconesMendoza.servicios.UsuarioServicio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioService;
    
    @Autowired
    private MailServicio mailService;

    @GetMapping("/form")
    public String crearUsuario(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Usuario> optional = usuarioService.findAllByQ(id);
            if (optional.isPresent()) {
                model.addAttribute("usuarios", optional.get());
            } else {
                return "redirect:/login";
            }
        } else {
            model.addAttribute("usuario", new Usuario());
        }
        return "redirect:/usuario/form";
    }
    
    @PostMapping("/save")
    public String saveUsuario(Model model, RedirectAttributes redirect, @ModelAttribute Usuario usuario, @RequestParam("file") MultipartFile imagen, @RequestParam String password2) throws Exception {
        try {
            try {
                if (!imagen.isEmpty()){
                    Path directorioImagenes = Paths.get(".//src/main/resources/static/images/locacion/");
                    String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
                    byte[] bytesImg = imagen.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
                    Files.write(rutaCompleta, bytesImg);
                    usuario.setFoto(imagen.getOriginalFilename());
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            usuarioService.save(usuario, password2);
            mailService.enviarMail(usuario.getEmail(),"Bienvenido a Rincones Mendoza","Agradecemos que te hayas unido a nuestra plataforma y comiences a formar parte de esta hermosa familia de turistas para dejar tus recomendaciones, comentarios y puntuaciones :)");
            redirect.addFlashAttribute("success", "Usuario guardado con exito");
            return "redirect:/login";
        } catch (WebException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("persona", usuario);
            return "redirect:/usuario/form";
        }
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String listUsuario(Model model, @RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("usuarios", usuarioService.listAllByQ(q));
        } else {
            model.addAttribute("usuarios", usuarioService.listAll());
        }
        return "usuario-list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String eliminarUsuario(@RequestParam(required = true) String id) {
        usuarioService.deleteById(id);
        return "redirect:/usuario/list";
    }
}
