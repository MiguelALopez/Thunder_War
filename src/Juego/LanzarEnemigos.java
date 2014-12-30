/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 14-dic-2014
 * Nombre del Archivo: LanzarEnemigos.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego;

import javax.swing.*;
//Clase del juego. Lanza los enemigos
public class LanzarEnemigos extends Thread {

//    topGun ventanaJuego;
    VentanaJuego tp;
    int i = 0;
    int velocidadLanzaEne;

    LanzarEnemigos(JFrame j, int velocidadLanzaEne) {
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