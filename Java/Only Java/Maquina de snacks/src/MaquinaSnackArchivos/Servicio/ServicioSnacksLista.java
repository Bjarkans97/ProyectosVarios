package MaquinaSnackArchivos.Servicio;

import MaquinaSnackArchivos.Dominio.SnackEN;

import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksLista implements IServicioSnacks{
    private static final List<SnackEN> snacks;

    //Bloque de tipo static inicializador
    static {
        snacks = new ArrayList<>();
        snacks.add(new SnackEN("Papas", 70.99));
        snacks.add(new SnackEN("Doritos", 57.99));
        snacks.add(new SnackEN("Score", 12.99));
        snacks.add(new SnackEN("Monster", 10.99));
    }

    public void agregarSnack(SnackEN snack){
        snacks.add(snack);
    }

    public void mostrarSnack(){
        var inventarioSnacks = "";
        for (var snack : snacks) {
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println("**** Inventario de snacks ****");
        System.out.print(inventarioSnacks);
    }

    public List<SnackEN> getSnacks(){
        return snacks;
    }

}
