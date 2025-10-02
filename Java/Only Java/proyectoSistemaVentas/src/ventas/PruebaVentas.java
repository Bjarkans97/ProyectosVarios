package ventas;

import orden.OrdenEN;
import producto.ProductoEN;

public class PruebaVentas {
    public static void main(String[] args) {
        System.out.println("**** Sistema de ventas ****");

        var producto1 = new ProductoEN("Blusa", 89.99);

        //System.out.println(producto1);

        var orden = new OrdenEN();
        orden.agregarProducto(producto1);
        orden.mostrarOrden();
    }
}
