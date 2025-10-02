package Zona_Fit.DAL.Clases;

import Dominio.Cliente;
import Zona_Fit.DAL.Interfaces.IClienteDAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Conexion.Conexion;

public class ClienteDAL implements IClienteDAL {
    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = Conexion.getConnection();
        var sql = "CALL SelectClientesTodos()";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var cli = new Cliente();
                cli.Id = rs.getInt("ClienteID");
                cli.Nombre = rs.getString("Nombre");
                cli.Apellido = rs.getString("Apellido");
                cli.Membresia = rs.getInt("Membresia");
                clientes.add(cli);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: "+e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cli) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = Conexion.getConnection();
        var sql = "CALL SelectClienteID(?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cli.Id);
            rs = ps.executeQuery();
            if (rs.next()){
                cli.Nombre = rs.getString("Nombre");
                cli.Apellido = rs.getString("Apellido");
                cli.Membresia = rs.getInt("Membresia");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar conexion");
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }

        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cli) {
        PreparedStatement ps = null;
        Connection conn = Conexion.getConnection();
        var sql = "Call AgregarNuevoCliente(?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cli.Nombre);
            ps.setString(2, cli.Apellido);
            ps.setInt(3, cli.Membresia);
            ps.execute();
            return true;
        }catch (Exception e) {
            System.out.println("Error al agregar cliente: "+e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cli) {
        PreparedStatement ps = null;
        var sql = "Call ModificarCliente(?,?,?,?)";
        Connection conn = Conexion.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cli.Nombre);
            ps.setString(2, cli.Apellido);
            ps.setInt(3, cli.Membresia);
            ps.setInt(4, cli.Id);
            ps.execute();
            return true;
        }catch (Exception e) {
            System.out.println("Error al modificar cliente: "+e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cli) {
        PreparedStatement ps = null;
        var sql = "Call EliminarCliente(?)";
        Connection conn = Conexion.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cli.Id);
            ps.execute();
            return true;
        }catch (Exception e) {
            System.out.println("Error al eliminar cliente: "+e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
        return false;
    }

    static void main(String[] args) {
        //Listar Clientes
        //System.out.println("*** Listar clientes ***");
        IClienteDAL cliDao = new ClienteDAL();
        //var cliente = cliDao.listarCliente();
        //cliente.forEach(System.out::println);

        //Listar por id
        //System.out.println("*** Listar clientes id ***");
        //var clienteid = new Cliente(6);
        //var encontrado = cliDao.buscarClientePorId(clienteid);
        //if (encontrado){
        //    System.out.println("Cliente encontrado: "+clienteid);
        //}else {
        //    System.out.println("Cliente no encontrado");
        //}

        //Agregar cliente
        //System.out.println("*** Agregar cliente ***");
        //var clienteid = new Cliente("Nicolas", "FuenteMavida", 106);
        //var Agregado = cliDao.agregarCliente(clienteid);
        //if (Agregado){
        //    System.out.println("Cliente Agregado: "+clienteid);
        //}else {
        //    System.out.println("Cliente no agregado");
        //}

        //Modificar cliente
        //System.out.println("*** Modificar cliente ***");
        //var clienteid = new Cliente(4, "James", "Jhonson", 103);
        //var Agregado = cliDao.modificarCliente(clienteid);
        //if (Agregado){
        //    System.out.println("Cliente modificado: "+clienteid);
        //}else {
        //    System.out.println("Cliente no modificado");
        //}

        //Eliminar cliente
        //System.out.println("*** Eliminar cliente ***");
        //var clienteid = new Cliente(3);
        //var Agregado = cliDao.eliminarCliente(clienteid);
        //if (Agregado){
        //    System.out.println("Cliente eliminado: "+clienteid);
        //}else {
        //    System.out.println("Cliente no modificado");
        //}
    }

}
