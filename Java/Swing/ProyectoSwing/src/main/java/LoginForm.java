import javax.swing.*;
import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JPanel panelPrincipal;
    private JTextField lblUsuario;
    private JPasswordField lblPass;
    private JButton btnEnviar;

    public LoginForm(){
        inicializarForma();
        btnEnviar.addActionListener(e -> validar() );
    }

    private void inicializarForma() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
    }

    private void validar() {
        //Leer los valores ingresados
        var usu = this.lblUsuario.getText();
        var pass = new String(this.lblPass.getPassword());
        if ("root".equals(usu) && "admin".equals(pass)){
            mostrarMensaje("Datos correctos, Bienvenido");
        }else if("root".equals(usu)){
            mostrarMensaje("Contraseña incorrecta");
        }
        else if("admin".equals(pass)){
            mostrarMensaje("Usuario incorrecto");
        }else {
            mostrarMensaje("Datos incorrectos");
        }
    }

    private void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        LoginForm login = new LoginForm();
        login.setVisible(true);
    }

}
