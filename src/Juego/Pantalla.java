/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Código: 1326691
 * Fecha: 13-dic-2014
 * Nombre del Archivo: Pantalla.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego;

import javax.swing.*;
import java.awt.*;

public class Pantalla extends JPanel implements Runnable{
    ThunderWar thunderWar;
//    topGun thunderWar;
    Image imageBuffer;

    //Imagenes
    Image imgAvion;
    Image imgDisparo;
    Image imgEnemigo;
    Image imgExplosion;

    Graphics graphicsBuffer;

    Thread hilo;
    public Pantalla(JFrame jFrame){

        //Cargar Imagenes
        imgAvion = Toolkit.getDefaultToolkit().getImage("avion.png");
        imgDisparo = Toolkit.getDefaultToolkit().getImage("disparo.jpg");
        imgEnemigo = Toolkit.getDefaultToolkit().getImage("enemigo.png");
        imgExplosion = Toolkit.getDefaultToolkit().getImage("explosion.png");

        this.thunderWar = (ThunderWar) jFrame;

        hilo = new Thread(this);
    }



    public void paint(Graphics g) {
        if (thunderWar.iniciado){
            imageBuffer = createImage(getWidth(), getHeight());
            graphicsBuffer = imageBuffer.getGraphics();

            actualizarAvion(graphicsBuffer);
            actualizarDisparos(graphicsBuffer);
            actualizarEnemigos(graphicsBuffer);


            colisionAvionEnemigo(graphicsBuffer);
            colisionDisparoEnemigo();

            g.drawImage(imageBuffer, 0, 0, null);
        }

    }

    public void actualizarAvion(Graphics g) {
        //pintar aviones
        for (int i = 0; i < thunderWar.jugadores.length; i++){
            if (thunderWar.jugadores[i] != null){
                g.drawImage(imgAvion,
                        thunderWar.jugadores[i].getPosicionX(),
                        thunderWar.jugadores[i].getPosicionY(),
                        50, 50, null);
            }
        }
    }

    public void actualizarDisparos(Graphics g) {
        for (int i = 0; i < thunderWar.jugadores.length; i++){
            if (thunderWar.jugadores[i] != null){
                for (int j = 0; j < thunderWar.jugadores[i].disparos.length; j++){

                    int x = thunderWar.jugadores[i].disparos[j].retornarX();
                    int y = thunderWar.jugadores[i].disparos[j].retornarY();
                    g.drawImage(imgDisparo, x + 40, y, 3, 9, null);
                    g.drawImage(imgDisparo, x + 10, y, 3, 9, null);
                }
            }
        }
    }

    public void actualizarEnemigos(Graphics g) {
        for (int i = 0; i < thunderWar.enemig.length; i++) {
            int x = thunderWar.enemig[i].retornarX();
            int y = thunderWar.enemig[i].retornarY();
            if (thunderWar.enemig[i].muerto() == false)
                g.drawImage(imgEnemigo, x, y, 50, 50, null);
            else
                g.drawImage(imgExplosion, x, y, 50, 50, null);
        }
    }

    //Metodo que verifica si el avion de un jugador colisiona contra uno enemigo
    void colisionAvionEnemigo(Graphics g) {
        for (int i = 0; i < thunderWar.jugadores.length; i++){
            if (thunderWar.jugadores[i]!= null){
                for (int j = 0; j < thunderWar.enemig.length; j++) {
                    int w1, h1, w2, h2, x1, x2, y1, y2;
                    w1 = 50;
                    h1 = 50;
                    w2 = 50;
                    h2 = 50;
                    //Posicion del avion
                    x1 = thunderWar.jugadores[i].getPosicionX();
                    y1 = thunderWar.jugadores[i].getPosicionY();

                    //Posicion del enemigo
                    x2 = thunderWar.enemig[j].retornarX();
                    y2 = thunderWar.enemig[j].retornarY();

                    //Comprobar posiciones enemigo y disparos
                    if (((x1 + w1) > x2) && ((y1 + h1) > y2) && ((x2 + w2) > x1) && ((y2 + h2) > y1)) {
                        if (thunderWar.enemig[j].muerto() == false) {
                            thunderWar.jugadores[i].golpe();
                            thunderWar.vida.setValue(thunderWar.jugadores[i].getVida());


                        }
                        thunderWar.enemig[j].explotar();
                        thunderWar.jugadores[i].disparos[j].explotar();
                        if (thunderWar.checkJugadoresVivos()){
                            thunderWar.terminarPartida();

                        }
                    }
                }
            }
        }
    }

    void colisionDisparoEnemigo() {
        for (int i= 0; i < thunderWar.jugadores.length; i++){
            if (thunderWar.jugadores[i] != null){
                for (int j = 0; j < thunderWar.jugadores[i].disparos.length; j++){
                    for (int k = 0; k < thunderWar.enemig.length; k++){
                        int w1, h1, w2, h2, x1, y1, x2, y2;
                        w1 = 50;
                        h1 = 50;
                        w2 = 43;
                        h2 = 9;

                        //Posicion del disparo
                        x1 = thunderWar.jugadores[i].disparos[j].retornarX();
                        y1 = thunderWar.jugadores[i].disparos[j].retornarY();

                        //Posicion del enemigo
                        x2 = thunderWar.enemig[k].retornarX();
                        y2 = thunderWar.enemig[k].retornarY();

                        //Comprobar posiciones enemigo y disparos
                        if (((x1 + w1) > x2) && ((y1 + h1) > y2) && ((x2 + w2) > x1) && ((y2 + h2) > y1)) {
                            thunderWar.enemig[k].explotar();
                            thunderWar.jugadores[i].disparos[j].explotar();
                        }
                    }
                }
            }
        }
    }


    public synchronized void terminarPartida(){
//        graphicsBuffer.clearRect(0, 0, 600, 600);
//        graphicsBuffer.drawString("Fin del juego", 250, 350);

        hilo.stop();

    }

    @Override
    public void run() {
        while (true){
            repaint();
            try {
                hilo.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
