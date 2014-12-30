/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 14-dic-2014
 * Nombre del Archivo: VentanaJuego.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

public class VentanaJuego extends JFrame{


    AudioClip clipDisparo;

    JTextField textFieldPuntos;
    JPanel panelBarraEstado;
    JProgressBar progressBarVida;
    JTextField textFieldTiempo;
    Temporizador temp;

    boolean iniciado;

    //Clases
    Disparo disp[];
    Enemigo enemig[];
    LanzarEnemigos lanzarEnemigos;
    Jugador jugadores[];
    Pantalla pantalla;

    public VentanaJuego(){
        super("Thunder War v 1.0");
        setLayout(new BorderLayout());
        iniciado = false;

        //Inicializar objetos
        panelBarraEstado = new JPanel();
        textFieldPuntos = new JTextField(5);
        textFieldTiempo = new JTextField(5);
        progressBarVida = new JProgressBar(0, 100);

        panelBarraEstado.setLayout(new FlowLayout());
        panelBarraEstado.add(new JLabel("Puntos"));
        panelBarraEstado.add(textFieldPuntos);
        textFieldPuntos.setText("0");
        panelBarraEstado.add(new JLabel("Vida"));
        panelBarraEstado.add(progressBarVida);
        panelBarraEstado.add(new JLabel("Tiempo"));
        panelBarraEstado.add(textFieldTiempo);
        progressBarVida.setValue(100);
        progressBarVida.setForeground(Color.RED);
        add(panelBarraEstado, BorderLayout.SOUTH);

        cargarSonidos();

        //Inicializo las clases
        jugadores = new Jugador[10];


        setSize(600, 650);
        setResizable(false);
        setVisible(false);
    }

    public synchronized void terminarPartida() {
        lanzarEnemigos.stop();
        pantalla.terminarPartida();
        iniciado = false;
        pantalla.setVisible(false);
        JPanel panelFinPartida = new JPanel();
        panelFinPartida.setLayout(new BorderLayout());
        JLabel labelFinPartida =new JLabel("Fin Partida");
        labelFinPartida.setHorizontalAlignment(JLabel.CENTER);
        panelFinPartida.add(labelFinPartida, BorderLayout.CENTER);
        add(panelFinPartida, BorderLayout.CENTER);
    }



    //Carga datos iniciales del juego
    public void iniciarJuego(Jugador jugador, int nivel, int tiempo){
        //Inicio añado el jugador principal a la primera posicion del arreglo
        jugadores[0] = jugador;
        pantalla = new Pantalla(this);
        switch (nivel){
            case 0:{
                nivelUno(tiempo);
            }break;
            case 1:{
                nivelDos(tiempo);
            }break;
            case 2:{
                nivelTres(tiempo);
            }
        }
    }

    public void arcade(){

    }

    public void nivelUno(int tiempo){
        // envia datos a lanzar Enemigo para darle un nv de dif
        jugadores[0].iniciarPartida(100, 20, 18, getWidth(), getHeight(), 0, 1, 10, clipDisparo);
        enemig = new Enemigo[7];
        iniciado = true;

        temp = new Temporizador(this,tiempo);
        temp.start();

        for (int i = 0; i < enemig.length; i++) {
            //Iniciar los enemigos
            enemig[i] = new Enemigo(this, 4);
            enemig[i].start();
            enemig[i].preparar();
        }

        lanzarEnemigos = new LanzarEnemigos(this, 1500);
        lanzarEnemigos.start();

        pantalla.hilo.start();
        add(pantalla, BorderLayout.CENTER);
    }

    public void nivelDos(int tiempo){
        // envia datos a lanzar Enemigo para darle un nv de dif
        jugadores[0].iniciarPartida(100, 16, 16, getWidth(), getHeight(), 0, 2, 20, clipDisparo);
        enemig = new Enemigo[10];
        iniciado = true;

        temp = new Temporizador(this,tiempo);
        temp.start();

        for (int i = 0; i < enemig.length; i++) {
            //Iniciar los enemigos
            enemig[i] = new Enemigo(this, 6);
            enemig[i].start();
            enemig[i].preparar();
        }

        lanzarEnemigos = new LanzarEnemigos(this, 1000);
        lanzarEnemigos.start();

        pantalla.hilo.start();
        add(pantalla, BorderLayout.CENTER);
    }

    public void nivelTres(int tiempo){
        // envia datos a lanzar Enemigo para darle un nv de dif
        jugadores[0].iniciarPartida(100, 12, 14, getWidth(), getHeight(), 0, 3, 30, clipDisparo);
        enemig = new Enemigo[20];
        iniciado = true;

        temp = new Temporizador(this,tiempo);
        temp.start();

        for (int i = 0; i < enemig.length; i++) {
            //Iniciar los enemigos
            enemig[i] = new Enemigo(this, 8);
            enemig[i].start();
            enemig[i].preparar();
        }

        lanzarEnemigos = new LanzarEnemigos(this, 500);
        lanzarEnemigos.start();

        pantalla.hilo.start();
        add(pantalla, BorderLayout.CENTER);
    }

    //Metodo encargado de chequear si todos los jugadores estan muertos
    public boolean checkJugadoresVivos(){
        boolean muertos = true;
        for (int i = 0; i < jugadores.length; i++)
        {
            if (jugadores[i] != null)
            {
                if (jugadores[i].getVida() > 0){
                    muertos = false;
                }
            }
        }
        return muertos;
    }

    public Jugador getJugador(int numJugador){
        try{
            return jugadores[numJugador];
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("El jugador no se encuentra");
            return null;
        }
    }

    public void cargarSonidos(){
        try{
            clipDisparo = Applet.newAudioClip(VentanaJuego.class.getResource("disparo.aiff"));
        }catch (NullPointerException e){
            System.err.println("Error al cargar los audios");
        }

    }

}
