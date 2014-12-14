/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Código: 1326691
 * Fecha: 14-dic-2014
 * Nombre del Archivo: ManejoPuntuacion.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManejoPuntuacion {
    private Connection connection = null;

    public ManejoPuntuacion(Connection connection) {
        this.connection = connection;
    }

    public void crearPuntuacion(String nick_name, int puntaje) {
        PreparedStatement consulta = null;
        String insertSQL = "INSERT INTO puntajes" +
                "(nick_name_user, puntaje)" +
                "VALUES (?, ?)";

        try {
            consulta = connection.prepareStatement(insertSQL);

            consulta.setString(1, nick_name);
            consulta.setInt(2, puntaje);

            consulta.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la consulta en " + e.getLocalizedMessage());
        } catch (NullPointerException e) {
            System.out.println("Error Conexion nula en " + e.getLocalizedMessage());
        }
    }
}
