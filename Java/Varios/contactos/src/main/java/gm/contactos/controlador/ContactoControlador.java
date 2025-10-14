package gm.contactos.controlador;

import gm.contactos.modelo.Contacto;
import gm.contactos.repositorio.ContactoRepositorio;
import gm.contactos.servicio.ContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    ContactoServicio contactoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Contacto> contactos = contactoServicio.listaContactos();
        contactos.forEach((contacto -> logger.info(contacto.toString())));
        modelo.put("contactos", contactos);
        return "index"; //index.html
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(){
        return "agregar"; //Redirecciona a agregar.html
    }

    @PostMapping("/agregar")
    public String agregarContacto(@ModelAttribute("contactoForma") Contacto contacto){
        logger.info("Contacto a agregar: "+contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/"; //Redirigimos al path para que se recargue toda la pagina
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value = "id") Integer idContacto, ModelMap modelo){
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
        logger.info("Contacto a editar: "+contacto);
        modelo.put("contacto",contacto);
        return "editar"; //Redirecciona a agregar.html
    }

    @PostMapping("/editar")
    public String editarContacto(@ModelAttribute("contacto") Contacto contacto){
        logger.info("Contacto a editar: "+contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable(value = "id") Integer idContacto){
        Contacto contacto = new Contacto();
        contacto.setIdContacto(idContacto);
        contactoServicio.eliminarContacto(contacto);
        logger.info("Contacto a eliminar: "+contacto);
        return "redirect:/"; //Redirecciona a agregar.html
    }
}
