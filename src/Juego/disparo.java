package Juego;

import javax.swing.*;

class disparo extends Thread {

    //Posicion del disparo y velocidad de los disparos
    int y = 0;
    int x = 0;
    int velocidadNave;
    int velocidadEnemigos;
    int numJugador;
    int numeroEnemigo = 0;
    int velocidadDisparo;
    //Clase de topGun

    //enemigo ==true si el disparo es hecho por un avion enemigo
    boolean enemigo = false;
    //numeroEnemigo devuelve el numero del avion que efectua el disparo


//    topGun thunderWar;
    ThunderWar tg;
    //Constructor para una nave de el jugador
    disparo(JFrame jf, int velocidadNave, int numJugador, int velocidadDisparo) {
//        thunderWar = (topGun) jf;
        tg = (ThunderWar) jf;

        this.velocidadNave = velocidadNave;
        this.numJugador = numJugador;
        this.velocidadDisparo = velocidadDisparo;
    }
    //Constructor para una nave enemiga
    disparo(JFrame jf, boolean e, int velocidadEnemigos, int velocidadDisparo) {
//        thunderWar = (topGun) jf;
        tg = (ThunderWar) jf;
        enemigo = e;
        this.velocidadEnemigos = velocidadEnemigos;
        this.velocidadDisparo = velocidadDisparo;
    }

    public void run()    //metodo run, obligatorio en el thread
    {
        System.out.println(y);
        if (!enemigo) {
            //Posicion del avion
            x = tg.jugadores[numJugador].getPosicionX();
            y = tg.jugadores[numJugador].getPosicionY();
        } else {
            //Posicion Enemigo
            x = tg.enemig[numeroEnemigo].retornarX();
            y = tg.enemig[numeroEnemigo].retornarY();
        }


        while (y > 0) {
            try {
                //Mover Disparo
                if (enemigo) y = y + velocidadEnemigos;
                else y = y - velocidadNave;
                //Retardar 30 milisegundos
                sleep(velocidadDisparo);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
        }
        //Volver al estado inicial para preparar de nuevo el disparo
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
        x = tg.jugadores[numJugador].getPosicionX();
        y = tg.jugadores[numJugador].getPosicionY();
        resume();
    }

    public void fuego(int numero) {
        //Posicion del avion
//        x = thunderWar.retornarX();
//        y = thunderWar.retornarY();
        x = tg.jugadores[numJugador].getPosicionX();
        y = tg.jugadores[numJugador].getPosicionY();

        numeroEnemigo = numero;
        resume();
    }

    //Motodos que retornan la posicion del disparo

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