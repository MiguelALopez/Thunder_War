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
//    topGun thunderWar;
    ThunderWar thunderWar;
    enemigo(JFrame jf, int movimiento, int velocidadEnemigo) {
        thunderWar = (ThunderWar) jf;
        this.velocidadEnemigo = velocidadEnemigo;
//        this.velocidadCreacion = velocidadCreacion;
    }


    public void run()    //metodo run, obligatorio en el thread
    {
        //posicion aleatoria del avion enemigo
//        double aux = Math.random() * 550;
        tamano = thunderWar.getWidth() - thunderWar.pantalla.imgEnemigo.getWidth(null);
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
        //Volver al estado inicial para preparar de nuevo el disparo
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
        puntos = Integer.valueOf(thunderWar.puntos.getText());
        puntos += 10;
        thunderWar.puntos.setText(String.valueOf(puntos));
    }

    public void fuego() {
        x = thunderWar.jugadores[0].getPosicionX();
        y = thunderWar.jugadores[0].getPosicionY();
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