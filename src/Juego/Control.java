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
package Juego;

import Juego.DAO.ConnectionConf;
import Juego.DAO.ManejoPuntuacion;
import Juego.DAO.ManejoUsuario;
import Juego.Interfaz.Autentificar;

import java.awt.event.*;

public class Control {
    // Sincronizado
    VentanaJuego ventanaJuego;
    ConnectionConf connectionConf;

    public  Control(final Autentificar autentificar, final ManejoUsuario manejoUsuario, ManejoPuntuacion manejoPuntuacion){
        autentificar.buttonIniciar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nick_name = autentificar.textNomUsuario.getText();
                        String password = autentificar.passwordFieldUsuario.getText();
//                        System.out.println(nick_name + " - " + password);
                        if (manejoUsuario.autentificar(nick_name,password)){
                            System.out.println("esta registrado");
                        }else {
                            System.out.println("no esta registrado");
                        }
                    }
                }
        );
    }

    public Control(final VentanaJuego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;

        //Eventos para los movimientos de la nave
        ventanaJuego.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (ventanaJuego.iniciado && ventanaJuego.jugadores[0].vivo) {
                            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                                ventanaJuego.jugadores[0].disparar();
                            }if (e.getKeyCode() == KeyEvent.VK_UP) {
                                ventanaJuego.jugadores[0].arriba();
                            }if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                                ventanaJuego.jugadores[0].abajo();
                            }if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                                ventanaJuego.jugadores[0].izquierda();
                            }if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                                ventanaJuego.jugadores[0].derecha();
                            }
                        }
                    }
                }
        );
    }
}
