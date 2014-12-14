/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Código: 1326691
 * Fecha: 14-dic-2014
 * Nombre del Archivo: ManejoUsuario.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego.DAO;

import java.awt.*;
import java.sql.*;

public class ManejoUsuario {
    private Connection connection = null;

    public ManejoUsuario(Connection connection) {
        this.connection = connection;
    }

    /*Metodo encargado de buscar si un usuario se encuentra registrado en la base de datos,
    * Retorna true en caso de que exista y false de lo contrario*/
    public boolean existUsuario(String nick_name) {
        Boolean existe = false;
        String consulta = "SELECT nick_name FROM usuario WHERE nick_name= '" + nick_name + "';";
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(consulta);
            if (resultSet.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta en" + e.getLocalizedMessage());
        }

        return existe;
    }

    public void crearUsuario(String nick_name, String password, String url_imagen, String nombre, String apellido,
                             int tipo_nave, boolean sonido) {
        if (!existUsuario(nick_name)) // Es lo mismo que existUsuario(nom_usuario) != true
        {
            PreparedStatement consulta = null;
            String insertSQL = "INSERT  INTO usuario" +
                    "(nick_name, password, url_image, nombre, apellido, tipo_nave, sonido, estado)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                consulta = connection.prepareStatement(insertSQL);

                consulta.setString(1, nick_name);
                consulta.setString(2, password);
                consulta.setString(3, url_imagen);
                consulta.setString(4, nombre);
                consulta.setString(5, apellido);
                consulta.setInt(6, tipo_nave);
                consulta.setBoolean(7, sonido);
                consulta.setBoolean(8, true);

                consulta.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El usuario " + nick_name + " ya existe");
        }
    }

    public void modificarUsuario(String nick_name, String password, String url_imagen, String nombre, String apellido,
                                 int tipo_nave, boolean sonido) {
        if (existUsuario(nick_name)) {
            PreparedStatement consulta = null;

            String editSQL = "UPDATE usuario SET password=?, url_image=?, nombre=?, apellido=?, tipo_nave=?," +
                    "sonido=?";
            try {
                consulta = connection.prepareStatement(editSQL);

                consulta.setString(1, password);
                consulta.setString(2, url_imagen);
                consulta.setString(3, nombre);
                consulta.setString(4, apellido);
                consulta.setInt(5, tipo_nave);
                consulta.setBoolean(6, sonido);

                consulta.executeUpdate();

                System.out.println("Modificacion del Usuario " + nick_name + " Realizada con exito");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El Usuario " + nick_name + " no existe");
        }
    }

    public void eliminarUsuario(String nick_name)
    {
        if (existUsuario(nick_name))
        {
            PreparedStatement consulta = null;
            String editStateSQL = "UPDATE usuario SET estado=? WHERE nick_name=?";

            try {
                consulta = connection.prepareStatement(editStateSQL);

                consulta.setBoolean(1, false);
                consulta.setString(2, nick_name);

                consulta.executeUpdate();

                System.out.println("El usuario " + nick_name + " Fue Eliminado con Exito");
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("El usuario" + nick_name + " no existe");
        }
    }

    public ResultSet buscarUsuario(String nick_name) throws NullPointerException
    {
        ResultSet tabla = null;
        if (existUsuario(nick_name))
        {
            //Consulta que busca a un usuario por su nombre de usuario y que aun este habilitado en la base de datos
            String consulta = "SELECT * FROM usuario WHERE nick_name='" + nick_name + "' and  estado=TRUE;";
            try {
                Statement statement = connection.createStatement();
                tabla = statement.executeQuery(consulta);
                System.out.println("Consulta realizada con exito");
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("El usuario " + nick_name + " no existe");
        }
        return tabla;
    }

    public ResultSet buscarPuntuacion(String nick_name) throws NullPointerException
    {
        ResultSet tabla = null;
        if (existUsuario(nick_name))
        {
            //Consulta que busca a un usuario por su nombre de usuario y que aun este habilitado en la base de datos
            String consulta = "SELECT nick_name, puntaje FROM usuario, puntajes WHERE nick_name='" + nick_name + "' and  estado=TRUE;";
            try {
                Statement statement = connection.createStatement();
                tabla = statement.executeQuery(consulta);
                System.out.println("Consulta realizada con exito");
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("El usuario " + nick_name + " no existe");
        }
        return tabla;
    }
}
