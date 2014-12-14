/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Código: 1326691
 * Fecha: 13-dic-2014
 * Nombre del Archivo: Control.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego;

import java.awt.event.*;

public class Control {
    // Sincronizado
    ThunderWar thunderWar;
//    topGun thunderWar;
//    Pantalla pantalla;

    public Control(final ThunderWar thunderWar) {
        this.thunderWar = thunderWar;
//        this.pantalla = pantalla;

        thunderWar.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        //Eventos para los movimientos de la nave
        thunderWar.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (thunderWar.iniciado) {
                            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                                thunderWar.jugadores[0].disparar();
                            }
                            if (e.getKeyCode() == KeyEvent.VK_UP) {
                                thunderWar.jugadores[0].arriba();
                            }
                            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                                thunderWar.jugadores[0].abajo();
                            }
                            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                                thunderWar.jugadores[0].izquierda();
                            }
                            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                                thunderWar.jugadores[0].derecha();
                            }
                        }
                    }
                }
        );

        thunderWar.inicio.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        thunderWar.inicio.setVisible(false);
                        Jugador jugador = new Jugador("miguel",true, null, null, null, thunderWar);
                        thunderWar.iniciarJuego(jugador);
                    }
                }
        );
    }
}
