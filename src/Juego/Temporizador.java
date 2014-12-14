package Juego;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class Temporizador extends Thread {

    private int segundos       = 0;
    private int numeroSegundos = 0;
    ThunderWar tp;


    public void run() {
        while(segundos < numeroSegundos) {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {}
            segundos+=1;
            tp.tiempo.setText(Integer.toString(numeroSegundos-segundos));
        }
        tp.terminarPartida();
        suspend();
    }

    public Temporizador(ThunderWar tp, int numeroSegundos) {
        this.tp = tp;
        this.numeroSegundos = numeroSegundos;



    }
}

