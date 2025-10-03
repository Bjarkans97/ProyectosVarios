package gm.zona_fit.Servicio;

import gm.zona_fit.Modelo.Cliente;
import gm.zona_fit.Repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio{

    @Autowired
    public ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClientePorID(Integer idCli) {
        Cliente cliente = clienteRepositorio.findById(idCli).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cli) {
        clienteRepositorio.save(cli);//Si existe, modifica el dato, si no lo guarda

    }

    @Override
    public void eliminarCliente(Cliente cli) {
        clienteRepositorio.delete(cli);
    }
}
