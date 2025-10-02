package Zona_Fit.DAL.Interfaces;

import java.util.List;
import Dominio.Cliente;

public interface IClienteDAL {
    List<Cliente> listarCliente();
    boolean buscarClientePorId(Cliente cli);
    boolean agregarCliente (Cliente cli);
    boolean modificarCliente(Cliente cli);
    boolean eliminarCliente (Cliente cli);
}
