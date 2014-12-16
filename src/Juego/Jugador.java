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
    int numJugador;
    boolean sonido;
    int tipoImgNave;
    disparo disparos[];

    //Clases
    VentanaJuego ventanaJuego;

    public Jugador(String nombre, boolean sonido, int tipoImgNave,  VentanaJuego ventanaJuego) {
        this.nombre = nombre;
        this.sonido = sonido;
        this.tipoImgNave = tipoImgNave;
        this.ventanaJuego = ventanaJuego;
        numDisparo = 0;
        puntos = 0;
    }

    public void iniciarPartida(int vida, int numDisparos, int velocidadNave, int width, int heigth, int numJugador, int velocidadDisparo){
        this.vida = vida;
        this.velocidadNave = velocidadNave;
        this.numJugador = numJugador;

        disparos = new disparo[numDisparos];
        vivo = true;
        posicionY = width-90;
        posicionX = (heigth - 90) / 2;

        for (int i = 0; i < disparos.length; i++) {
            //Iniciamos los disparos
            if (i < disparos.length){
                disparos[i] = new disparo(ventanaJuego, 24, numJugador, velocidadDisparo); //Disparos del avion
            }
            else {
//                disparos[i] = new disparo(ventanaJuego,true, 6); //Disparos de los enemigos
            }
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
    }

    public void golpe(){
        vida -= 10;
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

    public boolean isSonido() {
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
}
