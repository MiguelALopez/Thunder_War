/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 14-dic-2014
 * Nombre del Archivo: Disparo.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego;

import javax.swing.*;

class Disparo extends Thread {

    //Posicion del Disparo y velocidad de los disparos
    int y = 0;
    int x = 0;
    int velocidadNave;
    int velocidadEnemigos;
    int numJugador;
    int numeroEnemigo = 0;
    int velocidadDisparo;
    boolean disparado = false;

    //Enemigo ==true si el Disparo es hecho por un avion Enemigo
    boolean enemigo = false;
    VentanaJuego ventanaJuego;

    //Constructor para una nave de el jugador
    Disparo(JFrame jf, int numJugador, int velocidadDisparo) {
        ventanaJuego = (VentanaJuego) jf;

        this.velocidadNave = velocidadNave;
        this.numJugador = numJugador;
        this.velocidadDisparo = velocidadDisparo;
    }
    //Constructor para una nave enemiga
    Disparo(JFrame jf, boolean e, int velocidadDisparo) {
        ventanaJuego = (VentanaJuego) jf;
        enemigo = e;
        this.velocidadEnemigos = velocidadEnemigos;
        this.velocidadDisparo = velocidadDisparo;
    }

    public void run()    //metodo run, obligatorio en el thread
    {
        if (!enemigo) {
            //Posicion del avion
            x = ventanaJuego.jugadores[numJugador].getPosicionX();
            y = ventanaJuego.jugadores[numJugador].getPosicionY();
        } else {
            //Posicion Enemigo
            x = ventanaJuego.enemig[numeroEnemigo].retornarX();
            y = ventanaJuego.enemig[numeroEnemigo].retornarY();
        }

        //Mejorar esta instruccion para que funciones con los dos tipos de Disparo
        while (y > 0) {
            try {
                //Mover Disparo
                if (enemigo) y = y + 1;
                else y = y - 1;
                //Retardar 30 milisegundos
                sleep(velocidadDisparo);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
        }
        //Volver al estado inicial para preparar de nuevo el Disparo
        disparado =false;
        preparar();
        run();
    }

    public void preparar() {
        suspend();
    }

    public void explotar() {
        preparar();
        y = 700;
    }

    public void fuego() {
        //Posicion del avion
        x = ventanaJuego.jugadores[numJugador].getPosicionX();
        y = ventanaJuego.jugadores[numJugador].getPosicionY();
        disparado = true;
        resume();
    }

    public void fuego(int numero) {
        //Posicion del avion
//        x = ventanaJuego.retornarX();
//        y = ventanaJuego.retornarY();
        x = ventanaJuego.jugadores[numJugador].getPosicionX();
        y = ventanaJuego.jugadores[numJugador].getPosicionY();

        numeroEnemigo = numero;
        resume();
    }

    //Motodos que retornan la posicion del Disparo

    public int retornarY() {
        return y;
    }

    public int retornarX() {
        return x;
    }

    public void setVelocidadNave(int velocidad){
        this.velocidadNave = velocidadNave;
    }
}