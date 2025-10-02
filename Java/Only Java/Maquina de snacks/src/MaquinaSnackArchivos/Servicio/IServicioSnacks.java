package MaquinaSnackArchivos.Servicio;

import MaquinaSnackArchivos.Dominio.SnackEN;

import java.util.List;

public interface IServicioSnacks {
    void agregarSnack(SnackEN snack);
    void mostrarSnack();
    List<SnackEN> getSnacks();

}
