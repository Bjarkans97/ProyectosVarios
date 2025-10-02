package orden;

import producto.ProductoEN;

public class OrdenEN {
    public final int idOrden;
    public ProductoEN[] productos;
    public int contadorProductos;
    public static final int MAX_PRODUCTOS = 10;
    public static int contadorOrdenes;

    public OrdenEN(){
        this.idOrden = ++OrdenEN.contadorOrdenes;
        this.productos = new ProductoEN[OrdenEN.MAX_PRODUCTOS];
    }

    public void agregarProducto(ProductoEN producto){
        if (this.contadorProductos < this.MAX_PRODUCTOS){
            this.productos[this.contadorProductos++] = producto;
        }else {
            System.out.println("Ha superado el máximo de productos que puede agregar");
        }
    }

    public double calcularTotal(){
        double total = 0;

        for (var i = 0; i < this.contadorProductos; i++){
            total = total + this.productos[i].precio;
        }
        return total;
    }

    public void mostrarOrden(){
        System.out.println("Id orden: "+ this.idOrden);
        var totalOrden = this.calcularTotal();
        System.out.println("\nTotal de la orden: $"+totalOrden);
        System.out.println("\tProductos de la orden: ");
        for (var i = 0; i < this.contadorProductos; i++){
            System.out.println("\t\t"+this.productos[i]);
        }
    }
}
