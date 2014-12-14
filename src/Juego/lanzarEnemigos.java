/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Código: 1326691
 * Fecha: 13-dic-2014
 * Nombre del Archivo: lanzarEnemigos.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego;

import javax.swing.*;
//Clase del juego. Lanza los enemigos
public class lanzarEnemigos extends Thread {

//    topGun thunderWar;
    ThunderWar tp;
    int i = 0;
    int velocidadLanzaEne;

    lanzarEnemigos(JFrame j, int velocidadLanzaEne) {
        tp = (ThunderWar) j;
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