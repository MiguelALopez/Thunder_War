package Juego;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Alejandro
 */
public class Temporizador extends Thread {

    private int segundos       = 0;
    private int numeroSegundos = 0;
    ThunderWar thunderWar;


    public void run() {
        while(segundos < numeroSegundos) {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {}
            segundos+=1;
            thunderWar.tiempo.setText(Integer.toString(numeroSegundos-segundos));
        }
        thunderWar.terminarPartida();

        suspend();
    }

    public Temporizador(ThunderWar tp, int numeroSegundos) {
        this.thunderWar = tp;
        this.numeroSegundos = numeroSegundos;



    }
}

