/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Autor: Manuel Alejandro Mena Salazar
 * Autor: Luis Carlos Montalvo
 * Código: 1326691 -1329107 - 1329088
 * Fecha: 14-dic-2014
 * Nombre del Archivo: ConnectionConf.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConf {
    private String url, usuario, password;
    private Connection conexion =null;
    public ConnectionConf(){
        url = "jdbc:mysql://localhost:3306/ThunderWar";
        usuario="root";
        password="";
    }
    public Connection conectar(){
        conexion = null;
        try {
            // Se carga el driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println( "Driver Cargado" );
        } catch(ClassNotFoundException  e ) {
            System.out.println( "No se pudo cargar el driver." );
        }

        try{

            //Crear el objeto de conexion a la base de datos
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println( "Conexion Abierta" );
            //Crear objeto Statement para realizar queries a la base de datos
        } catch(SQLException e) {
            System.out.println( "No se pudo abrir la bd." );
        }
        return conexion;
    }

    public Connection getConnection(){
        if (conexion == null) {
            return this.conectar();
        }
        else{
            return conexion;
        }
    }

    public void closeConnection(){
        try{
            if (conexion != null){
                conexion.close();
            }

        } catch(SQLException  e) {
            System.out.println( "No se pudo cerrar." );
        }
    }
}
