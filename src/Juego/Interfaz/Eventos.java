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
package Juego.Interfaz;

import Juego.DAO.DataBase;
import Juego.Jugador;
import Juego.VentanaJuego;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Eventos {
    private Autentificar autentificar;
    private Menu menu;
    private VentanaJuego juego;
    private DataBase dataBase;
    private Jugador jugador;

    public Eventos(final Autentificar ventanaAutentificar, final CrearUsuario crearUsuario, Menu ventanaMenu, final VentanaJuego ventanaJuego) {
        this.autentificar = ventanaAutentificar;
        this.menu = ventanaMenu;
        this.juego = ventanaJuego;
        dataBase = new DataBase();

        autentificar.buttonIniciar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nick_usuario = autentificar.textNomUsuario.getText();
                        String password = autentificar.passwordFieldUsuario.getText();

                        if(dataBase.getUsuaro().autentificar(nick_usuario, password)){
                            ResultSet resultSet = dataBase.getUsuaro().buscarUsuario(nick_usuario);
                            try {
                                while (resultSet.next()) {
                                    String nickName = resultSet.getString(1);
                                    int tipoImgNave = resultSet.getInt(5);
                                    boolean sonido = resultSet.getBoolean(6);
                                    jugador = new Jugador(nickName, sonido, tipoImgNave,ventanaJuego);
                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            System.out.println("esta registrado");
                            autentificar.setVisible(false);
                            menu.setVisible(true);



                        }else {
                            JOptionPane.showMessageDialog(null,"Nombre de usuario y/o contraseña incorrecta");
                        }
                    }
                }
        );
        autentificar.buttonRegistrarse.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        crearUsuario.setVisible(true);
                    }
                }
        );
        crearUsuario.buttonRegistrar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nikName = crearUsuario.textFieldNickName.getText();
                        String password = crearUsuario.passwordFieldContraseña.getText();
                        String nombre =crearUsuario.textFieldNombre.getText();
                        String apellido = crearUsuario.textFieldApellido.getText();
                        int tipoAvion = crearUsuario.jComboBoxTipoNave.getSelectedIndex();

                        dataBase.getUsuaro().crearUsuario(nikName, password, nombre, apellido, tipoAvion, true);
                        crearUsuario.setVisible(false);
                    }
                }
        );
        crearUsuario.buttonCancelar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        crearUsuario.setVisible(false);
                    }
                }
        );

        menu.buttonArcade.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        juego.setVisible(true);
                        juego.iniciarJuego(jugador);
//                        juego.

                    }
                }
        );

        menu.buttonOpciones.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menu.panelContButIni.setVisible(false);
                        menu.panelContButOpciones.setVisible(true);
                    }
                }
        );

    }


}
