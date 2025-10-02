package Dominio;

import java.util.Objects;

public class Cliente {
    public int Id;
    public String Nombre;
    public String Apellido;
    public int Membresia;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, int membresia) {
        Nombre = nombre;
        Apellido = apellido;
        Membresia = membresia;
    }

    public Cliente(int id) {
        Id = id;
    }

    public Cliente(int id, String nombre, String apellido, int membresia) {
        this(nombre, apellido, membresia);
        Id = id;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    public int getMembresia() {
        return Membresia;
    }
    public void setMembresia(int membresia) {
        Membresia = membresia;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Membresia=" + Membresia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Id == cliente.Id && Membresia == cliente.Membresia && Objects.equals(Nombre, cliente.Nombre) && Objects.equals(Apellido, cliente.Apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Nombre, Apellido, Membresia);
    }
}
