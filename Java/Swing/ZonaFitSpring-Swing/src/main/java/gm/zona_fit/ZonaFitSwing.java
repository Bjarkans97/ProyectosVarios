package gm.zona_fit;

import com.formdev.flatlaf.FlatDarculaLaf;
import gm.zona_fit.Gui.ZonaFitForm;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ZonaFitSwing {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        //Instancia la fabrica de spring
        ConfigurableApplicationContext contextSpring =
                new SpringApplicationBuilder(ZonaFitSwing.class)
                        .headless(false)
                        .web(WebApplicationType.NONE)
                        .run(args);

        //Crear un objeto de Swing
        SwingUtilities.invokeLater(()->{
            ZonaFitForm zonaFitForm = contextSpring.getBean(ZonaFitForm.class);
            zonaFitForm.setVisible(true);
        });
    }
}
