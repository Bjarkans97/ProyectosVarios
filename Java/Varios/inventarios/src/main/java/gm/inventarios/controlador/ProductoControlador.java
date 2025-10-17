package gm.inventarios.controlador;


import gm.inventarios.modelo.Producto;
import gm.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventario-app") // http://localhost:8080/inventario-app
@CrossOrigin(value = "http://localhost:4200") //Puerto por default de angular

public class ProductoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    ProductoServicio productoServicio;

    @GetMapping("/productos") // http://localhost:8080/inventario-app/productos
    public List<Producto> obtenerProductos(){
        List<Producto> productos = this.productoServicio.listaProductos();
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("producto a agregar: "+producto);
        return this.productoServicio.guardarProducto(producto);
    }
}
