package gm.contactos.servicio;

import gm.contactos.modelo.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gm.contactos.repositorio.ContactoRepositorio;

import java.util.List;

@Service
public class ContactoServicio implements IContactoServicio{

    @Autowired
    ContactoRepositorio contactoRepositorio;

    @Override
    public List<Contacto> listaContactos() {
        return contactoRepositorio.findAll();
    }

    @Override
    public Contacto buscarContactoPorId(int id) {
        Contacto contacto = contactoRepositorio.findById(id).orElse(null);
        return contacto;
    }

    @Override
    public void guardarContacto(Contacto contacto) {
        contactoRepositorio.save(contacto);
    }

    @Override
    public void eliminarContacto(Contacto contacto) {
        contactoRepositorio.delete(contacto);
    }
}
