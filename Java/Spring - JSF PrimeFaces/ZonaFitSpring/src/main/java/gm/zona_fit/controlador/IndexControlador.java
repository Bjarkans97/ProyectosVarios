package gm.zona_fit.controlador;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@EqualsAndHashCode
@ToString
@ViewScoped //Me sirve ya que va a ser una aplicacion de solo una vista

public class IndexControlador {

    @Autowired
    IClienteServicio clienteServicio;
    private List<Cliente> clientes;
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);
    private Cliente clienteSel;

    @PostConstruct //Se manda a llamar despues de crear la instancia de la clase
    public void init(){
        cargarDatos();
    }

    private void cargarDatos() {
        this.clientes = this.clienteServicio.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente(){
        this.clienteSel = new Cliente();
    }

    public void eliminarCliente(){
        try {
            this.clienteServicio.eliminarCliente(this.clienteSel);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente eliminado"));

            //Eliminar de la lista
            this.clientes.remove(this.clienteSel);

            //Actualizar la tabla usando ajax
            PrimeFaces.current().ajax().update("forma-clientes:mensajes", "forma-clientes:clientes-tabla");

            //Resetea ClienteSel
            this.clienteSel = null;
        } catch (Exception e) {
            logger.info("Error al eliminar: "+e);
        }
    }

    public void guardarCliente(){
        logger.info("Cliente a guardar: "+this.clienteSel);

        //Agregar
        if (this.clienteSel.getClienteID() == null){
            this.clienteServicio.guardarCliente(this.clienteSel);
            this.clientes.add(this.clienteSel);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Agregado"));
        }
        //Modificar
        else {
            this.clienteServicio.guardarCliente(this.clienteSel);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente modificado"));
        }

        //Ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");

        //Actualizar la tabla usando ajax
        PrimeFaces.current().ajax().update("forma-clientes:mensajes", "forma-clientes:clientes-tabla");

        //Resetea ClienteSel
        this.clienteSel = null;
    }

}
