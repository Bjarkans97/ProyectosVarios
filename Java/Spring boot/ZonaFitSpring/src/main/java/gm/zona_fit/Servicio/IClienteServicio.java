package gm.zona_fit.Servicio;

import gm.zona_fit.Modelo.Cliente;

import java.util.List;

public interface IClienteServicio {
    public List<Cliente> listarClientes();
    public Cliente buscarClientePorID(Integer idCli);
    public void guardarCliente(Cliente cli);
    public void eliminarCliente(Cliente cli);
}
