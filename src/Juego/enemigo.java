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

class enemigo extends Thread {

    //Posicion del disparo
    int y = -50;
    int x = 0;

    int tamano;

    boolean vivo = true;
    boolean derecha = true;
    int velocidadEnemigo = 6;
    int velocidadCreacion;

    //Clase de topGun
//    topGun ventanaJuego;
    VentanaJuego ventanaJuego;
    enemigo(JFrame jf, int velocidadEnemigo) {
        ventanaJuego = (VentanaJuego) jf;
        this.velocidadEnemigo = velocidadEnemigo;
//        this.velocidadCreacion = velocidadCreacion;
    }


    public void run()    //metodo run, obligatorio en el thread
    {
        //posicion aleatoria del avion enemigo
//        double aux = Math.random() * 550;
        tamano = ventanaJuego.getWidth() - ventanaJuego.pantalla.imgEnemigo.getWidth(null);
        double aux = Math.random() * tamano;
        x = (int) aux;
        //direccion aleatoria del enemigo
        if (x % 2 == 0) {
            derecha = true;
        }
        else {
            derecha = false;
        }
        //Velocidad aleatoria del enemigo
        aux = Math.random() * (velocidadEnemigo + -1) + velocidadEnemigo;
        int movimiento = (int) aux;
        while (y < tamano && vivo == true) {
            try {
                sleep(50);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
            if (derecha) {
                x += movimiento;
            }
            else {
                x -= movimiento;
            }
            if (x < 10) {
                derecha = true;
            }
            if (x > tamano) {
                derecha = false;
            }
            y += movimiento;
        }
        //Volver al panelBarraEstado inicial para preparar de nuevo el disparo
        if (!vivo){
            try {
                sleep(350);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
        }
        y = -60;
        preparar();
        vivo = true;
        run();
    }

    boolean muerto() {
        return !vivo;
    }

    public void preparar() {
        suspend();
    }

    public void explotar() {
        int puntos;
        vivo = false;
        puntos = Integer.valueOf(ventanaJuego.textFieldPuntos.getText());
        puntos += 10;
        ventanaJuego.textFieldPuntos.setText(String.valueOf(puntos));
    }

    public void fuego() {
        x = ventanaJuego.jugadores[0].getPosicionX();
        y = ventanaJuego.jugadores[0].getPosicionY();
        resume();
    }

    //Metodos que retornan la posicion del disparo

    public int retornarY() {
        return y;
    }

    public int retornarX() {
        return x;
    }
}