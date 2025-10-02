package MaquinaSnackArchivos.Servicio;

import MaquinaSnackArchivos.Dominio.SnackEN;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksArchivos implements IServicioSnacks{
    private final String NOMBRE_ARCHIVO = "snacks.txt";
    //Crear la lista de snacks
    private List<SnackEN> snacks = new ArrayList<>();

    //Constructor


    public ServicioSnacksArchivos() {
        //Creamos el archivo
        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;
        try {
            existe = archivo.exists();
            if (existe){
                this.snacks = obtenerSnacks();

            }else {
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Archivo creado");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado: "+e);
        }

        //Si no existe le cargamos data
        if (!existe){
            cargarArchivosIniciales();
        }
    }

    private List<SnackEN> obtenerSnacks() {
        var snacks = new ArrayList<SnackEN>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for (String linea : lineas){
                String[] lineaSnack = linea.split(",");
                var idSnack = lineaSnack[0];
                var nombre = lineaSnack[1];
                double precio = Double.parseDouble(lineaSnack[2]);

                var snack = new SnackEN(nombre, precio);
                snacks.add(snack);
            }

        } catch (Exception e) {
            System.out.println("Error al leer archivo: "+e);
        }
        return snacks;
    }

    private void cargarArchivosIniciales() {
        this.agregarSnack(new SnackEN("papas", 70));
        this.agregarSnack(new SnackEN("refresco", 50));
        this.agregarSnack(new SnackEN("sandwich", 65));
    }

    @Override
    public void agregarSnack(SnackEN snack) {
        //Agregamos el nuevo snack
        this.snacks.add(snack);

        this.agreagarSnackArchivo(snack);
    }

    private void agreagarSnackArchivo(SnackEN snack) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close();
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
    }

    @Override
    public void mostrarSnack() {
        System.out.println("---- Snacks en el inventario ----");
        var inventarioSnack = "";
        for (var snack : this.snacks){
            inventarioSnack += snack.toString() + "\n";

        }
        System.out.println(inventarioSnack);
    }

    @Override
    public List<SnackEN> getSnacks() {
        return this.snacks;
    }
}
