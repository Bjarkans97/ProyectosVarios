package gm.tareas.presentacion;

import gm.tareas.TareasApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SistemaTareasFX extends Application {

    private ConfigurableApplicationContext context;
    //public static void main(String[] args) {
    //    launch(args);
    //}

    @Override
    public void init(){
        this.context = new SpringApplicationBuilder(TareasApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(TareasApplication.class.getResource("/templates/index.fxml"));

        loader.setControllerFactory(context::getBean); //Proporsiona los objetos de tipo bean (JavaFX)
        Scene escena = new Scene(loader.load());
        primaryStage.setScene(escena);
        primaryStage.show();
    }

    @Override
    public void stop(){
        context.close();
        Platform.exit();
    }
}
