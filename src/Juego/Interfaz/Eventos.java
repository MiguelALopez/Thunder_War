/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 14-dic-2014
 * Nombre del Archivo: Eventos.java
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
                                    String passwordUser = resultSet.getString(2);
                                    String nombre = resultSet.getString(3);
                                    String apellido = resultSet.getString(4);
                                    int tipoImgNave = resultSet.getInt(5);
                                    boolean sonido = resultSet.getBoolean(6);
                                    jugador = new Jugador(nickName,passwordUser,nombre, apellido, tipoImgNave, sonido,ventanaJuego);
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
        crearUsuario.jComboBoxTipoNave.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(crearUsuario.jComboBoxTipoNave.getSelectedItem().equals("1")){
                            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion1.png"));
                            crearUsuario.buttonNave.setIcon(imagen);
                        }
                        if(crearUsuario.jComboBoxTipoNave.getSelectedItem().equals("2")){
                            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion2.png"));
                            crearUsuario.buttonNave.setIcon(imagen);
                        }
                        if(crearUsuario.jComboBoxTipoNave.getSelectedItem().equals("3")){
                            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion3.png"));
                            crearUsuario.buttonNave.setIcon(imagen);
                        }
                        if(crearUsuario.jComboBoxTipoNave.getSelectedItem().equals("4")){
                            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion4.png"));
                            crearUsuario.buttonNave.setIcon(imagen);
                        }
                        if(crearUsuario.jComboBoxTipoNave.getSelectedItem().equals("5")){
                            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion5.png"));
                            crearUsuario.buttonNave.setIcon(imagen);
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
                        menu.panelContArcadeIni.setVisible(true);
                        menu.panelContButOpciones.setVisible(false);
//                        menu.panelContButIni.setVisible(false);
                    }
                }
        );

        menu.buttonOpciones.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menu.panelContArcadeIni.setVisible(false);
                        menu.panelContButOpciones.setVisible(true);

                        menu.passwordFieldContraseña.setText(jugador.getPassword());
                        menu.textFieldNombre.setText(jugador.getNombre());
                        menu.textFieldApellido.setText(jugador.getApellido());
                        menu.jComboBoxTipoNave.setSelectedIndex(jugador.getTipoImgNave());
                        checkIconNavesMenu();
                        if (!jugador.getSonido()){
                            menu.buttonSonido.setText("OFF");
                        }
                    }
                }
        );

        menu.buttonSonido.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (menu.buttonSonido.getText().equals("ON")){
                            menu.buttonSonido.setText("OFF");
                        }else {
                            menu.buttonSonido.setText("ON");
                        }
                    }
                }
        );
        menu.buttonGuardar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nick_name = jugador.getNick_name();
                        String password = menu.passwordFieldContraseña.getText();
                        String nombre = menu.textFieldNombre.getText();
                        String apellido = menu.textFieldApellido.getText();
                        int tipoNave = menu.jComboBoxTipoNave.getSelectedIndex();
                        boolean sonido = false;
                        if (menu.buttonSonido.getText().equals("ON")){
                            sonido = true;
                        }

                        dataBase.getUsuaro().modificarUsuario(nick_name, password, nombre, apellido, tipoNave, sonido);

                        menu.panelContButIni.setVisible(true);
                        menu.panelContButOpciones.setVisible(false);
                    }
                }
        );
        menu.jComboBoxTipoNave.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        checkIconNavesMenu();
                    }
                }
        );
        menu.buttonIniciarJuego.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(menu.jComboBoxNivel.getSelectedIndex() + " - ");
                        ventanaJuego.iniciarJuego(jugador, menu.jComboBoxNivel.getSelectedIndex(),
                                Integer.parseInt(menu.jSpinnerTiempo.getValue().toString()));
                        ventanaJuego.setVisible(true);
                        ventanaJuego.requestFocus();
                    }
                }
        );

    }

    public void checkIconNavesMenu(){
        if(menu.jComboBoxTipoNave.getSelectedItem().equals("1")){
            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion1.png"));
            menu.buttonNave.setIcon(imagen);
        }
        if(menu.jComboBoxTipoNave.getSelectedItem().equals("2")){
            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion2.png"));
            menu.buttonNave.setIcon(imagen);
        }
        if(menu.jComboBoxTipoNave.getSelectedItem().equals("3")){
            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion3.png"));
            menu.buttonNave.setIcon(imagen);
        }
        if(menu.jComboBoxTipoNave.getSelectedItem().equals("4")){
            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion4.png"));
            menu.buttonNave.setIcon(imagen);
        }
        if(menu.jComboBoxTipoNave.getSelectedItem().equals("5")){
            ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion5.png"));
            menu.buttonNave.setIcon(imagen);
        }
    }

}
