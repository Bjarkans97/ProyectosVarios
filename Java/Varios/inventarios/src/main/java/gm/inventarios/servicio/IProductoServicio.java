package gm.inventarios.servicio;

import gm.inventarios.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    List<Producto> listaProductos();
    Producto buscarProductoPorId(Integer idProducto);
    Producto guardarProducto(Producto producto);
    void eliminarProducto(Integer idProducto);
}
