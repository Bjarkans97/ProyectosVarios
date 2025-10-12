package gm.empleados.servicio;

import gm.empleados.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
    public List<Empleado> listarEmpleados();
    public void guardarEmpleado(Empleado emp);
    public void eliminarEmpleado(Empleado emp);
    public Empleado buscarEmpleadoPorId(Integer id);
}
