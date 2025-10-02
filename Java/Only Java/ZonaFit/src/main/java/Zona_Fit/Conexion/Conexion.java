package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection(){
        Connection conn = null;
        var db = "zonafit";
        var url = "jdbc:mysql://localhost:3306/"+db;
        var usu = "root";
        var pass = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usu, pass);
        } catch (Exception e) {
            System.out.println("Error al conectar a la db: "+e);
        }
        return conn;
    }

    public static void main(String[] args) {
        var con = Conexion.getConnection();
        if (con!=null) {
            System.out.println("Conexion exitosa: " + con);
        }else {
            System.out.println("Error al conectar");
        }
    }
}
