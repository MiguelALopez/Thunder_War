/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Código: 1326691
 * Fecha: 13-dic-2014
 * Nombre del Archivo: Jugador.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego;

import java.applet.AudioClip;
import java.awt.*;

public class Jugador{

    //Datos de un jugador
    String nombre;

    //Datos de la nave
    int vida;
    int puntos;
    int posicionX;
    int posicionY;
    int velocidadNave;
    boolean vivo;
    int numDisparo;

    //Configuraciones de un jugador
    boolean sonido;
    Image imgAvion;
    Image imgUsuario;
    AudioClip sonidoDisparo;
    disparo disparos[];

    //Clases
    ThunderWar thunderWar;

    public Jugador(String nombre, boolean sonido, Image imgAvion, Image imgUsuario, AudioClip sonidoDisparo,  ThunderWar thunderWar) {
        this.nombre = nombre;
        this.sonido = sonido;
        this.imgAvion = imgAvion;
        this.imgUsuario = imgUsuario;
        this.sonidoDisparo = sonidoDisparo;
        this.thunderWar = thunderWar;
        numDisparo = 0;
        puntos = 0;
    }

    public void iniciarPartida(int vida, int numDisparos, int velocidadNave, int width, int heigth){
        this.vida = vida;
        this.velocidadNave = velocidadNave;

        disparos = new disparo[numDisparos];
        posicionY = width-90;
        posicionX = (heigth - 90) / 2;

        for (int i = 0; i < disparos.length; i++) {
            //Iniciamos los disparos
            if (i < disparos.length/2){
                disparos[i] = new disparo(thunderWar, 24); //Disparos del avion
            }
            else {
                disparos[i] = new disparo(thunderWar,true, 6); //Disparos de los enemigos
            }
            disparos[i].start();
            //Preparar los disparos para utilizarlos al pulsar espacio
            disparos[i].preparar();
        }

    }

    void disparar() {
        numDisparo++;
        if (numDisparo == disparos.length/2) {
            numDisparo = 0;
        }
        disparos[numDisparo].fuego();
    }

    public void golpe(){
        vida -= 10;
        if (vida <= 0)
        {
            vivo = false;
        }
    }

    void abajo() {
        posicionY += velocidadNave;
    }

    void arriba() {
        posicionY -= velocidadNave;
    }

    void izquierda() {
        posicionX -= velocidadNave;
    }

    void derecha() {
        posicionX += velocidadNave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public boolean isSonido() {
        return sonido;
    }

    public void setSonido(boolean sonido) {
        this.sonido = sonido;
    }

    public Image getImgAvion() {
        return imgAvion;
    }

    public void setImgAvion(Image imgAvion) {
        this.imgAvion = imgAvion;
    }

    public Image getImgUsuario() {
        return imgUsuario;
    }

    public void setImgUsuario(Image imgUsuario) {
        this.imgUsuario = imgUsuario;
    }

    public AudioClip getSonidoDisparo() {
        return sonidoDisparo;
    }

    public void setSonidoDisparo(AudioClip sonidoDisparo) {
        this.sonidoDisparo = sonidoDisparo;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getVelocidadNave() {
        return velocidadNave;
    }

    public void setVelocidadNave(int velocidadNave) {
        this.velocidadNave = velocidadNave;
    }

    public disparo[] getDisparos() {
        return disparos;
    }

    public void setDisparos(disparo[] disparos) {
        this.disparos = disparos;
    }
}
