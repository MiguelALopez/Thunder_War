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

import java.applet.AudioClip;

public class Jugador{

    //Datos y configuraciones de un jugador
    String nick_name;
    String password;
    String nombre;
    String apellido;
    int tipoImgNave;
    boolean sonido;
    int numJugador;


    //Datos de la nave
    int vida;
    int puntos;
    int posicionX;
    int posicionY;
    int velocidadNave;
    int danoPorGolpe;
    boolean vivo;
    int numDisparo;
    AudioClip clipDisparo;



    disparo disparos[];

    //Clases
    VentanaJuego ventanaJuego;

    public Jugador(String nick_name, String password, String nombre, String apellido, int tipoImgNave,
                   boolean sonido, VentanaJuego ventanaJuego) {
        this.nick_name = nick_name;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoImgNave = tipoImgNave;
        this.sonido = sonido;
        this.ventanaJuego = ventanaJuego;

        numDisparo = 0;
        puntos = 0;
    }

    public void iniciarPartida(int vida, int numDisparos, int velocidadNave, int width, int heigth, int numJugador,
                               int velocidadDisparo, int danoPorGolpe, AudioClip clipDisparo){
        this.vida = vida;
        this.velocidadNave = velocidadNave;
        this.numJugador = numJugador;
        this.danoPorGolpe = danoPorGolpe;
        this.clipDisparo = clipDisparo;

        disparos = new disparo[numDisparos];
        vivo = true;
        posicionY = width-90;
        posicionX = (heigth - 90) / 2;

        for (int i = 0; i < disparos.length; i++) {
            //Iniciamos los disparos
            disparos[i] = new disparo(ventanaJuego, numJugador, velocidadDisparo); //Disparos del avion

            disparos[i].start();
            //Preparar los disparos para utilizarlos al pulsar espacio
            disparos[i].preparar();
        }

    }

    void disparar() {
        numDisparo++;
        if (numDisparo == disparos.length) {
            numDisparo = 0;
        }
        disparos[numDisparo].fuego();
        if (sonido = true){
            clipDisparo.stop();
            clipDisparo.play();
        }
    }

    public void golpe(){
        vida -= danoPorGolpe;
        System.out.println("El jugador " + numJugador + " tiene " + vida + " de vida");
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

    public boolean getSonido() {
        return sonido;
    }

    public void setSonido(boolean sonido) {
        this.sonido = sonido;
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

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTipoImgNave() {
        return tipoImgNave;
    }

    public void setTipoImgNave(int tipoImgNave) {
        this.tipoImgNave = tipoImgNave;
    }

    public int getNumJugador() {
        return numJugador;
    }

    public void setNumJugador(int numJugador) {
        this.numJugador = numJugador;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
}
