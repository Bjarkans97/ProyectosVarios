package gm.tareas.controlador;

import gm.tareas.modelo.Tarea;
import gm.tareas.servicio.TareaServicio;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexControlador implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private TareaServicio tareaServicio;

    @FXML
    private TableView<Tarea> tareaTabla;
    @FXML
    private TableColumn<Tarea, Integer> idTarea;
    @FXML
    private TableColumn<Tarea, String> NombreTarea;
    @FXML
    private TableColumn<Tarea, String> responsable;
    @FXML
    private TableColumn<Tarea, String> status;

    private final ObservableList<Tarea> tareaList = FXCollections.observableArrayList();

    //Mapero de textArea
    @FXML
    private TextField nombreTareaTexto;
    @FXML
    private TextField responsableTexto;
    @FXML
    private TextField estadoTexto;

    private Integer idTareaInterno;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listaTareas();
    }

    private void listaTareas() {
        tareaList.clear();
        tareaList.addAll(tareaServicio.listarTareas());
        tareaTabla.setItems(tareaList);
    }

    private void configurarColumnas() {//Relaciona los datos que cargaremos
        idTarea.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        NombreTarea.setCellValueFactory(new PropertyValueFactory<>("NombreTarea"));
        responsable.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void agregarTarea(){
        if (nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error de validacion", "Debe proporcionar una tarea");
            nombreTareaTexto.requestFocus();
            return;
        }else {
            var tarea = new Tarea();
            recolectaDatosFormulario(tarea);
            tareaServicio.guardarTarea(tarea);
            mostrarMensaje("Informacion", "Tarea agregada");
            limpiarFormulario();
            listaTareas();
        }
    }

    public void cargarTareaFormulario(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if (tarea!=null){
            idTareaInterno = tarea.getIdTarea();
            nombreTareaTexto.setText(tarea.getNombreTarea());
            responsableTexto.setText(tarea.getResponsable());
            estadoTexto.setText(tarea.getStatus());
        }
    }

    public void eliminarTarea(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if (tarea!=null){
            logger.info("Registro a eliminar: "+tarea.toString());
            tareaServicio.eliminarTarea(tarea);
            mostrarMensaje("Informacion", "Tarea eliminada "+tarea.getIdTarea());
            limpiarFormulario();
            listaTareas();
        }else {
            mostrarMensaje("Error de validacion", "Debe seleccionar un registro");
        }

    }

    public void modificarTarea(){
        if (idTareaInterno==null){
            mostrarMensaje("Informacion", "Debe de seleccionar un registro");
            return;
        }
        if (nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error de validacion", "Debe proporcionar una tarea");
            nombreTareaTexto.requestFocus();
            return;
        }
        if (idTareaInterno!=null){
            var tarea = new Tarea();
            recolectaDatosFormulario(tarea);
            tareaServicio.guardarTarea(tarea);
            mostrarMensaje("Informacion", "Tarea modificada");
            limpiarFormulario();
            listaTareas();
        }
    }

    public void limpiarFormulario() {
        idTareaInterno = null;
        nombreTareaTexto.clear();
        responsableTexto.clear();
        estadoTexto.clear();
    }

    private void recolectaDatosFormulario(Tarea tarea) {
        if (idTareaInterno != null){
            tarea.setIdTarea(idTareaInterno);
        }
        tarea.setNombreTarea(nombreTareaTexto.getText());
        tarea.setResponsable(responsableTexto.getText());
        tarea.setStatus(estadoTexto.getText());
    }

    private void mostrarMensaje(String errorDeValidacion, String debeProporcionarUnaTarea) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(errorDeValidacion);
        alerta.setHeaderText(null);
        alerta.setContentText(debeProporcionarUnaTarea);
        alerta.showAndWait();
    }
}
