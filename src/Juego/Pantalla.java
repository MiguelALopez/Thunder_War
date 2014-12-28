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
import java.awt.*;


public class Pantalla extends JPanel implements Runnable{
    VentanaJuego ventanaJuego;

    //Imagenes
    Image imgAvion;
    Image imgDisparo;
    Image imgEnemigo;
    Image imgExplosion;

    Image imageBuffer;
    Graphics graphicsBuffer;

    Thread hilo;
    public Pantalla(JFrame ventanaJuego){

        this.ventanaJuego = (VentanaJuego) ventanaJuego;
        cargarImagenes();
        hilo = new Thread(this);
    }


    public void cargarImagenes(){
        try{
            imgDisparo = new ImageIcon(getClass().getResource("Imagenes/disparo.jpg")).getImage();
            imgEnemigo = new ImageIcon(getClass().getResource("Imagenes/enemigo.png")).getImage();
            imgExplosion = new ImageIcon(getClass().getResource("Imagenes/explosion.png")).getImage();
            switch (ventanaJuego.jugadores[0].tipoImgNave){
                case 0:{
                    imgAvion = new ImageIcon(getClass().getResource("Imagenes/avion1.png")).getImage();
                }break;
                case 1:{
                    imgAvion = new ImageIcon(getClass().getResource("Imagenes/avion2.png")).getImage();
                }break;
                case 2:{
                    imgAvion = new ImageIcon(getClass().getResource("Imagenes/avion3.png")).getImage();
                }break;
                case 3:{
                    imgAvion = new ImageIcon(getClass().getResource("Imagenes/avion4.png")).getImage();
                }break;
                case 4:{
                    imgAvion = new ImageIcon(getClass().getResource("Imagenes/avion5.png")).getImage();
                }break;
            }
        }catch (NullPointerException e){
            System.err.println("Error al cargar las imagenes de las naves");
            imgAvion = Toolkit.getDefaultToolkit().getImage("");
            imgDisparo = Toolkit.getDefaultToolkit().getImage("");
            imgEnemigo = Toolkit.getDefaultToolkit().getImage("");
            imgExplosion = Toolkit.getDefaultToolkit().getImage("");
        }
    }



    public void paint(Graphics g) {
        if (ventanaJuego.iniciado){
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
        for (int i = 0; i < ventanaJuego.jugadores.length; i++){
            if (ventanaJuego.jugadores[i] != null && ventanaJuego.jugadores[i].vivo){
                g.drawImage(imgAvion,
                        ventanaJuego.jugadores[i].getPosicionX(),
                        ventanaJuego.jugadores[i].getPosicionY(),
                        50, 50, null);
            }
        }
    }

    public void actualizarDisparos(Graphics g) {
        for (int i = 0; i < ventanaJuego.jugadores.length; i++){
            if (ventanaJuego.jugadores[i] != null && ventanaJuego.jugadores[i].vivo){
                for (int j = 0; j < ventanaJuego.jugadores[i].disparos.length; j++){

                    int x = ventanaJuego.jugadores[i].disparos[j].retornarX();
                    int y = ventanaJuego.jugadores[i].disparos[j].retornarY();
                    g.drawImage(imgDisparo, x + 40, y, 3, 9, null);
                    g.drawImage(imgDisparo, x + 10, y, 3, 9, null);
                }
            }
        }
    }

    public void actualizarEnemigos(Graphics g) {
        for (int i = 0; i < ventanaJuego.enemig.length; i++) {
            int x = ventanaJuego.enemig[i].retornarX();
            int y = ventanaJuego.enemig[i].retornarY();
            if (ventanaJuego.enemig[i].muerto() == false)
                g.drawImage(imgEnemigo, x, y, 50, 50, null);
            else
                g.drawImage(imgExplosion, x, y, 50, 50, null);
        }
    }

    //Metodo que verifica si el avion de un jugador colisiona contra uno enemigo
    void colisionAvionEnemigo(Graphics g) {
        for (int i = 0; i < ventanaJuego.jugadores.length; i++){
            if (ventanaJuego.jugadores[i]!= null && ventanaJuego.jugadores[i].vivo){
                for (int j = 0; j < ventanaJuego.enemig.length; j++) {
                    int w1, h1, w2, h2, x1, x2, y1, y2;
                    w1 = 50;
                    h1 = 50;
                    w2 = 50;
                    h2 = 50;
                    //Posicion del avion
                    x1 = ventanaJuego.jugadores[i].getPosicionX();
                    y1 = ventanaJuego.jugadores[i].getPosicionY();

                    //Posicion del enemigo
                    x2 = ventanaJuego.enemig[j].retornarX();
                    y2 = ventanaJuego.enemig[j].retornarY();

                    //Comprobar posiciones enemigo y jugadores
                    if (((x1 + w1) > x2) && ((y1 + h1) > y2) && ((x2 + w2) > x1) && ((y2 + h2) > y1)) {
                        if (ventanaJuego.enemig[j].muerto() == false) {
                            ventanaJuego.jugadores[i].golpe();
                            if (i == 0){
                                ventanaJuego.progressBarVida.setValue(ventanaJuego.jugadores[0].getVida());
                            }
                        }
                        ventanaJuego.enemig[j].explotar(true);
                        if (ventanaJuego.checkJugadoresVivos()){
                            ventanaJuego.terminarPartida();

                        }
                    }
                }
            }
        }
    }

    void colisionDisparoEnemigo() {
        for (int i= 0; i < ventanaJuego.jugadores.length; i++){
            if (ventanaJuego.jugadores[i] != null){
                for (int j = 0; j < ventanaJuego.jugadores[i].disparos.length; j++){
                    for (int k = 0; k < ventanaJuego.enemig.length; k++){
                        int w1, h1, w2, h2, x1, y1, x2, y2;
                        w1 = 50;
                        h1 = 50;
                        w2 = 43;
                        h2 = 9;

                        //Posicion del disparo
                        x1 = ventanaJuego.jugadores[i].disparos[j].retornarX();
                        y1 = ventanaJuego.jugadores[i].disparos[j].retornarY();

                        //Posicion del enemigo
                        x2 = ventanaJuego.enemig[k].retornarX();
                        y2 = ventanaJuego.enemig[k].retornarY();

                        //Comprobar posiciones enemigo y disparos
                        if (((x1 + w1) > x2) && ((y1 + h1) > y2) && ((x2 + w2) > x1) && ((y2 + h2) > y1)) {
                            ventanaJuego.enemig[k].explotar(false);
                            ventanaJuego.jugadores[i].disparos[j].explotar();
                        }
                    }
                }
            }
        }
    }


    public synchronized void terminarPartida(){

        hilo.stop();
    }

    @Override
    public void run() {
        while (true){
            repaint();
            try {
                hilo.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
