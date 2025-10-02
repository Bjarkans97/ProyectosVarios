package producto;

public class ProductoEN {
    public final int idProducto;
    public String nombre;
    public double precio;
    public static int contadorProducto;

    public ProductoEN(String nombre, double precio){
        this.idProducto = ++ProductoEN.contadorProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public static int getContadorProducto() {
        return contadorProducto;
    }

    public static void setContadorProducto(int contadorProducto) {
        ProductoEN.contadorProducto = contadorProducto;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdProducto() {
        return this.idProducto;
    }

    @Override
    public String toString() {
        return "ProductoEN{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
