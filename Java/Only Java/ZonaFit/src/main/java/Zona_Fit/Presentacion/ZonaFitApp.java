package Zona_Fit.Presentacion;

import Zona_Fit.DAL.Clases.ClienteDAL;
import Zona_Fit.DAL.Interfaces.IClienteDAL;
import Dominio.Cliente;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);

        //Creamos el objeto
        IClienteDAL cliDAO = new ClienteDAL();
        while (!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, cliDAO, opcion);

            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: "+e);
            }
            System.out.println();
        }
    }

    private static boolean ejecutarOpciones(Scanner consola, IClienteDAL cliDAO, int opcion) {
        var salir = false;
        switch (opcion){
            case 1 -> { //Listar Clientes
                System.out.println("--- Listado de clientes ---");
                var clientes = cliDAO.listarCliente();
                for (Cliente cli : clientes){
                    System.out.print("Id: "+cli.Id);
                    System.out.print(" - Nombre cliente: "+cli.Nombre+" "+cli.Apellido);
                    System.out.print(" - N° Membresia: "+cli.Membresia);
                    System.out.println();
                }
            }
            case 2 -> {//Buscar cliente por id
                System.out.print("Ingrese el id del cliente a buscar: ");
                var cli = new Cliente(Integer.parseInt(consola.nextLine()));
                var encontrado = cliDAO.buscarClientePorId(cli);
                if (encontrado){
                    System.out.println("Cliente encontrado: "+cli.Nombre+" "+cli.Apellido+" N° Membresia: "+cli.Membresia);
                }else {
                    System.out.println("Cliente no encontrado");
                }
            }
            case 3 -> {//Agregar nuevo cliente
                System.out.print("Ingrese nombre del nuevo cliente: ");
                var nomcli = consola.nextLine();
                System.out.print("Ingrese Apellido del nuevo cliente: ");
                var apellcli = consola.nextLine();
                System.out.print("Ingrese N° membresia del nuevo cliente: ");
                var memcli = Integer.parseInt(consola.nextLine());
                var cli = new Cliente(nomcli, apellcli, memcli);
                var agregado = cliDAO.agregarCliente(cli);
                if (agregado){
                    System.out.println("Cliente agregado");
                }else {
                    System.out.println("Cliente no agregado");
                }
            }
            case 4 -> {//Modificar cliente
                System.out.print("Ingrese el id del cliente a modificar: ");
                var idcli = Integer.parseInt(consola.nextLine());
                System.out.print("Ingrese nombre del cliente: ");
                var nomcli = consola.nextLine();
                System.out.print("Ingrese Apellido del cliente: ");
                var apellcli = consola.nextLine();
                System.out.print("Ingrese N° membresia del cliente: ");
                var memcli = Integer.parseInt(consola.nextLine());
                var cli = new Cliente(idcli, nomcli, apellcli, memcli);
                var modificado = cliDAO.modificarCliente(cli);
                if (modificado){
                    System.out.println("Cliente modificado");
                }else {
                    System.out.println("Cliente no modificado");
                }
            }
            case 5 -> {//Eliminar cliente
                System.out.print("Ingrese el id del cliente a eliminar: ");
                var idcli = Integer.parseInt(consola.nextLine());
                var cli = new Cliente(idcli);
                var eliminado = cliDAO.eliminarCliente(cli);
                if (eliminado){
                    System.out.println("Cliente eliminado");
                }else {
                    System.out.println("Cliente no eliminado");
                }
            }
            case 6 -> {//Salir del programa
                System.out.println("*** Gracias por preferir Zona Fit GYM ***");
                salir = true;
            }
            default -> System.out.println("Opción no reconocida");

        }
        return salir;
    }


    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                **** Zona Fit GYM ***
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir\n
                Elige una opcion:\s""");
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }
}
