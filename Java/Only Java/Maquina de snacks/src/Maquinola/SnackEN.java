package Maquinola;

import java.io.Serializable;
import java.util.Objects;

public class SnackEN implements Serializable {
    private static int contadorSnack;
    private int idSnack;
    private String nombre;
    private double precio;

    public SnackEN() {
        this.idSnack = ++SnackEN.contadorSnack;
    }

    public SnackEN(String nombre, double precio){
        this();
        this.nombre = nombre;
        this.precio = precio;
    }

    public static int getContadorSnack() {
        return contadorSnack;
    }

    public static void setContadorSnack(int contadorSnack) {
        SnackEN.contadorSnack = contadorSnack;
    }

    public int getIdSnack() {
        return this.idSnack;
    }

    public void setIdSnack(int idSnack) {
        this.idSnack = idSnack;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "SnackEN{" +
                "idSnack=" + idSnack +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SnackEN snackEN = (SnackEN) o;
        return idSnack == snackEN.idSnack && Double.compare(precio, snackEN.precio) == 0 && Objects.equals(nombre, snackEN.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }
}
