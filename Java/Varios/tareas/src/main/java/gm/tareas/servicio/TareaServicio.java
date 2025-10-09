package gm.tareas.servicio;

import gm.tareas.modelo.Tarea;
import gm.tareas.repositorio.TareasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServicio implements ITareaServicio{

    @Autowired
    private TareasRepositorio tareasRepositorio;

    @Override
    public List<Tarea> listarTareas() {
        return tareasRepositorio.findAll();
    }

    @Override
    public Tarea buscarTareaPorId(Integer id) {
        Tarea tarea = tareasRepositorio.findById(id).orElse(null);
        return tarea;
    }

    @Override
    public void guardarTarea(Tarea tarea) {
        tareasRepositorio.save(tarea);
    }

    @Override
    public void eliminarTarea(Tarea tarea) {
        tareasRepositorio.delete(tarea);
    }
}
