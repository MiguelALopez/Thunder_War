/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 14-dic-2014
 * Nombre del Archivo: Temporizador.java
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

