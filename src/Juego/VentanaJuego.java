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

public class VentanaJuego extends JFrame{



//    JLabel lpuntos;
    JTextField textFieldPuntos;
    JPanel panelBarraEstado;
    JProgressBar progressBarVida;
    JTextField textFieldTiempo;
    Temporizador temp;

    boolean iniciado;

    //Clases
    disparo disp[];
    enemigo enemig[];
    lanzarEnemigos lanzarEnemigos;
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
        // envia datos a lanzar enemigo para darle un nv de dif
        jugadores[0].iniciarPartida(100, 20, 18, getWidth(), getHeight(), 0, 1, 10);
        enemig = new enemigo[7];
        iniciado = true;

        temp = new Temporizador(this,tiempo);
        temp.start();

        for (int i = 0; i < enemig.length; i++) {
            //Iniciar los enemigos
            enemig[i] = new enemigo(this, 4);
            enemig[i].start();
            enemig[i].preparar();
        }

        lanzarEnemigos = new lanzarEnemigos(this, 1500);
        lanzarEnemigos.start();

        pantalla.hilo.start();
        add(pantalla, BorderLayout.CENTER);
    }

    public void nivelDos(int tiempo){
        // envia datos a lanzar enemigo para darle un nv de dif
        jugadores[0].iniciarPartida(100, 16, 16, getWidth(), getHeight(), 0, 2, 20);
        enemig = new enemigo[10];
        iniciado = true;

        temp = new Temporizador(this,tiempo);
        temp.start();

        for (int i = 0; i < enemig.length; i++) {
            //Iniciar los enemigos
            enemig[i] = new enemigo(this, 6);
            enemig[i].start();
            enemig[i].preparar();
        }

        lanzarEnemigos = new lanzarEnemigos(this, 1000);
        lanzarEnemigos.start();

        pantalla.hilo.start();
        add(pantalla, BorderLayout.CENTER);
    }

    public void nivelTres(int tiempo){
        // envia datos a lanzar enemigo para darle un nv de dif
        jugadores[0].iniciarPartida(100, 12, 14, getWidth(), getHeight(), 0, 3, 30);
        enemig = new enemigo[20];
        iniciado = true;

        temp = new Temporizador(this,tiempo);
        temp.start();

        for (int i = 0; i < enemig.length; i++) {
            //Iniciar los enemigos
            enemig[i] = new enemigo(this, 8);
            enemig[i].start();
            enemig[i].preparar();
        }

        lanzarEnemigos = new lanzarEnemigos(this, 500);
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

}
