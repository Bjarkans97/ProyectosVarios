package gm.zona_fit;

import gm.zona_fit.Modelo.Cliente;
import gm.zona_fit.Servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

    @Autowired
    private IClienteServicio iClienteServicio;

    public static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
        logger.info("Iniciando la aplicación");
        //Levanta la fabrica de spring
        SpringApplication.run(ZonaFitApplication.class, args);
        logger.info("Aplicación finalizada");
	}

    @Override
    public void run(String... args) throws Exception {
        logger.info("**** Aplicacion Zona Fit Gym ****");

        var salir = false;
        var consola = new Scanner(System.in);

        while (!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion);
                logger.info("");
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: "+e);
            }
            System.out.println();
        }
    }
    private Integer mostrarMenu(Scanner consola) {
        logger.info("");
        logger.info("""
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir\n
                Elige una opcion:\s""");
        Integer opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }

    private boolean ejecutarOpciones(Scanner consola, Integer opcion) {
        var salir = false;
        switch (opcion){
            case 1 -> { //Listar Clientes
                logger.info("--- Listado de clientes ---");
                List<Cliente> clientes = iClienteServicio.listarClientes();
                for (Cliente cli : clientes){
                    System.out.print("Id: "+cli.ClienteID);
                    System.out.print(" - Nombre cliente: "+cli.Nombre+" "+cli.Apellido);
                    System.out.print(" - N° Membresia: "+cli.Membresia);
                    System.out.println();
                }
            }
            case 2 -> {//Buscar cliente por id
                logger.info("Ingrese el id del cliente a buscar: ");
                var idCli = Integer.parseInt(consola.nextLine());
                var cliente = iClienteServicio.buscarClientePorID(idCli);
                if (cliente!=null){
                    logger.info("Cliente encontrado: "+cliente.Nombre+" "+cliente.Apellido+" N° Membresia: "+cliente.Membresia);
                }else {
                    logger.info("Cliente no encontrado");
                }
            }
            case 3 -> {//Agregar nuevo cliente
                logger.info("Ingrese nombre del nuevo cliente: ");
                var nomcli = consola.nextLine();
                logger.info("Ingrese Apellido del nuevo cliente: ");
                var apellcli = consola.nextLine();
                logger.info("Ingrese N° membresia del nuevo cliente: ");
                var memcli = Integer.parseInt(consola.nextLine());
                var cli = new Cliente();
                cli.setNombre(nomcli);
                cli.setApellido(apellcli);
                cli.setMembresia(memcli);
                iClienteServicio.guardarCliente(cli);
                logger.info("Cliente agregado");
            }
            case 4 -> {//Modificar cliente
                logger.info("Ingrese el id del cliente a modificar: ");
                var idcli = Integer.parseInt(consola.nextLine());
                logger.info("Ingrese nombre del cliente: ");
                var nomcli = consola.nextLine();
                logger.info("Ingrese Apellido del cliente: ");
                var apellcli = consola.nextLine();
                logger.info("Ingrese N° membresia del cliente: ");
                var memcli = Integer.parseInt(consola.nextLine());
                var cli = new Cliente(idcli, nomcli, apellcli, memcli);
                iClienteServicio.guardarCliente(cli);
                logger.info("Cliente modificado");
            }
            case 5 -> {//Eliminar cliente
                logger.info("Ingrese el id del cliente a eliminar: ");
                var idcli = Integer.parseInt(consola.nextLine());
                var cli = new Cliente();
                cli.setClienteID(idcli);
                iClienteServicio.eliminarCliente(cli);
                logger.info("Cliente eliminado");
            }
            case 6 -> {//Salir del programa
                logger.info("*** Gracias por preferir Zona Fit GYM ***");
                salir = true;
            }
            default -> logger.info("Error al ingresar opción");
        }
        return salir;
    }



}
