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

import javax.swing.*;
//Clase del juego. Lanza los enemigos
public class lanzarEnemigos extends Thread {

//    topGun ventanaJuego;
    VentanaJuego tp;
    int i = 0;
    int velocidadLanzaEne;

    lanzarEnemigos(JFrame j, int velocidadLanzaEne) {
        tp = (VentanaJuego) j;
        this.velocidadLanzaEne = velocidadLanzaEne;
//        velocidadLanzaEne = 350;
    }

    public void run() {
        while (true) {
            i++;
            if (i == tp.enemig.length){
                i = 0;
            }
            try {
                sleep(velocidadLanzaEne);
            } catch (InterruptedException ie) {
            }
            tp.enemig[i].resume();
        }
    }

    public int getVelocidadLanzaEne() {
        return velocidadLanzaEne;
    }

    public void setVelocidadLanzaEne(int velocidadLanzaEne) {
        this.velocidadLanzaEne = velocidadLanzaEne;
    }
}