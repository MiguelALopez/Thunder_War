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

public class Temporizador extends Thread {

    private int segundos       = 0;
    private int numeroSegundos = 0;
    VentanaJuego ventanaJuego;


    public void run() {
        while(segundos < numeroSegundos) {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {}
            segundos+=1;
            ventanaJuego.textFieldTiempo.setText(Integer.toString(numeroSegundos-segundos));
        }
        ventanaJuego.terminarPartida();

        suspend();
    }

    public Temporizador(VentanaJuego tp, int numeroSegundos) {
        this.ventanaJuego = tp;
        this.numeroSegundos = numeroSegundos;
    }
}

