package gm.empleados.servicio;

import gm.empleados.modelo.Empleado;
import gm.empleados.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //para que se identifique como parte de spring
public class EmpleadoServicio implements IEmpleadoServicio{

    @Autowired //Para que se implemente de manera automatica
    private EmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public void guardarEmpleado(Empleado emp) {
        empleadoRepositorio.save(emp);
    }

    @Override
    public void eliminarEmpleado(Empleado emp) {
        empleadoRepositorio.delete(emp);
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer id) {
        Empleado empleado = empleadoRepositorio.findById(id).orElse(null);
        return empleado;
    }
}
