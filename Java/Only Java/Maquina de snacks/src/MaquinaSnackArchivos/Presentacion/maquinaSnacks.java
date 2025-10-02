package MaquinaSnackArchivos.Presentacion;

import MaquinaSnackArchivos.Dominio.SnackEN;
import MaquinaSnackArchivos.Servicio.IServicioSnacks;
import MaquinaSnackArchivos.Servicio.ServicioSnacksArchivos;
import MaquinaSnackArchivos.Servicio.ServicioSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class maquinaSnacks {
    public static void main(String[] args) {
        maquinaDeSnacks();
    }

    private static void maquinaDeSnacks() {
        var salir = false;
        var cons = new Scanner(System.in);

        //Creamos un objeto para el servicio de snacks
        //IServicioSnacks serviciosnacks = new ServicioSnacksLista();
        IServicioSnacks serviciosnacks = new ServicioSnacksArchivos();
        //Creamos la lista de productos de tipo snack
        List<SnackEN> producto = new ArrayList<>();
        System.out.println("---- Maquina de snacks ----");
        serviciosnacks.mostrarSnack();
        while (!salir){
            try {
                var opcion = mostrarMenu(cons);
                salir = ejecutarOpciones(opcion, cons, producto, serviciosnacks);


            } catch (Exception e) {
                System.out.println("Error inesperado: "+e);
            }
            finally {
                System.out.println("\n");//Imprime salto de linews por iteracion
            }
        }
    }

    private static boolean ejecutarOpciones(int opcion, Scanner cons, List<SnackEN> producto, IServicioSnacks servicioSnacks) {
        var salir = false;

        switch (opcion){
            case 1 -> comprarSnack(cons, producto, servicioSnacks);
            case 2 -> mostrarTicket(producto);
            case 3 -> agregaSnack(cons, servicioSnacks);
            case 4 -> salir = salirMaquina();
            default -> System.out.println("Opcion no valida");
        }

        return salir;
    }

    private static boolean salirMaquina() {
        System.out.println("*** Gracias por preferir \"Maquinola\" ***");
        return true;
    }

    private static void agregaSnack(Scanner cons, IServicioSnacks servicioSnacks) {
        System.out.print("Indique el snack a agregar: ");
        var nomSnack = cons.nextLine();
        System.out.print("Indique el valor del snack: ");
        var precioSnack = Double.parseDouble(cons.nextLine());
        try {
            servicioSnacks.agregarSnack(new SnackEN(nomSnack,precioSnack));
            System.out.println("Snack agregado correctamente!\n");
            servicioSnacks.mostrarSnack();
        }catch (Exception e){
            System.out.println("Error inesperado: "+e);
        }

    }

    private static void mostrarTicket(List<SnackEN> producto) {
        var total = 0;
        System.out.println("\n** Lista de productos a comprar **");
        for (SnackEN snac : producto){
            System.out.println("\t"+snac.getNombre()+" $"+ snac.getPrecio());
            total += snac.getPrecio();
        }
        System.out.println("\n\tTotal: $"+total);

    }

    private static void comprarSnack(Scanner cons, List<SnackEN> producto, IServicioSnacks servicioSnacks) {
        System.out.println("Que snack quieres comprar (id)?");
        int idSnack = Integer.parseInt(cons.nextLine());
        var snackEncontrado = false;
        for (var snack : servicioSnacks.getSnacks()) {
            if (idSnack == snack.getIdSnack()){
                //Si lo encuentra, lo agrega
                producto.add(snack);
                System.out.println("Producto agregado: "+snack);
                snackEncontrado = true;
                break;
            }
        }
        if(!snackEncontrado){
            System.out.println("Id de snack no encontrado!: "+idSnack);
        }
    }

    private static int mostrarMenu(Scanner cons) {
        System.out.print("""
                menú:
                1. Comprar Snack
                2. Mostrar ticket
                3. Agregar nuevo snack
                4. Salir
                Elige una opción:\s""");
        //Leemos y retornamos
        return  Integer.parseInt(cons.nextLine());
    }
}
