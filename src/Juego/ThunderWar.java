/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Código: 1326691
 * Fecha: 13-dic-2014
 * Nombre del Archivo: ThunderWar.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego;

import javax.swing.*;
import java.awt.*;

public class ThunderWar extends JFrame{


    JButton inicio;
    JLabel lpuntos;
    JTextField puntos;
    JPanel estado;
    JLabel lvida;
    JProgressBar vida;
    JLabel ltiempo;
    JTextField tiempo;
    Temporizador temp;

    boolean iniciado;

    //Clases
    disparo disp[];
    enemigo enemig[];
    lanzarEnemigos lanzarEnemigos;
    Jugador jugadores[];
    Control control;
//    ControlUsuario controlUsuario;
    Pantalla pantalla;
    //Constructor

    public ThunderWar(){
        //Inicializar objetos
        inicio = new JButton("Pulsa espacio para comenzar");
        lpuntos = new JLabel("Puntuacion");
        puntos = new JTextField(5);
        ltiempo = new JLabel("Tiempo");
        tiempo = new JTextField(5);
        estado = new JPanel();
        lvida = new JLabel("Vida");
        vida = new JProgressBar(0, 100);

        iniciado = false;

        //Inicializo las clases
        jugadores = new Jugador[10];
        pantalla = new Pantalla(this);
        control = new Control(this);


        setLayout(new BorderLayout());

        Font Letrilla = new Font("Roman", Font.BOLD, 35);
        inicio.setFont(Letrilla);
        inicio.setForeground(Color.RED);
//        inicio.addActionListener(this);
        add(inicio);

        /*Añadir interfaz de menu de inicio que escoje el nivel en que se desea jugar*/


        setSize(600, 650);
        setTitle("Thunder War v 1.0");
//        setResizable(false);
        setVisible(true);
    }

    public synchronized void terminarPartida() {
        lanzarEnemigos.stop();
        pantalla.terminarPartida();
        pantalla.setVisible(false);
        JPanel panelFinPartida = new JPanel();
        panelFinPartida.setLayout(new BorderLayout());
        JLabel labelFinPartida =new JLabel("Fin Partida");
        labelFinPartida.setHorizontalAlignment(JLabel.CENTER);
        panelFinPartida.add(labelFinPartida, BorderLayout.CENTER);
        add(panelFinPartida, BorderLayout.CENTER);
    }



    //Carga datos iniciales del juego
    public void iniciarJuego(Jugador jugador){
        //Inicio añado el jugador principal a la primera posicion del arreglo
        jugadores[0] = jugador;
        jugadores[1] = new Jugador("miguel",true, null, null, null, this);
        //Metodo encargado de cargar las configuraciones iniciales de un usuario
//        cargarConfiguraciones();
        nivelUno();
    }

    public void arcade(){
        int invecil = 0;

    }

    public void nivelUno(){
        // envia datos a lanzar enemigo para darle un nv de dif
        jugadores[0].iniciarPartida(100, 20, 18, getWidth(), getHeight());
        jugadores[1].iniciarPartida(100, 20, 20, getWidth()-50, getHeight()-50);
        enemig = new enemigo[5];
        iniciado = true;


        temp = new Temporizador(this,5);
        temp.start();


        for (int i = 0; i < enemig.length; i++) {
            //Iniciar los enemigos
            enemig[i] = new enemigo(this, 4, 3000);
            enemig[i].start();
            enemig[i].preparar();
        }

        lanzarEnemigos = new lanzarEnemigos(this, 1400);
        lanzarEnemigos.start();


        requestFocus();
        pantalla.hilo.start();


        //Panel de estado
        estado.setLayout(new FlowLayout());
        estado.add(lpuntos);
        estado.add(puntos);
        //Puntuacion a 0
        puntos.setText("0");
        estado.add(lvida);
        estado.add(vida);
        estado.add(ltiempo);
        estado.add(tiempo);
        //vida del avion
        vida.setValue(100);
        vida.setForeground(Color.RED);

        add(estado, BorderLayout.SOUTH);
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

    public void cargarConfiguraciones(){
        //Hacer
    }

    public static void main(String[] args) {
        ThunderWar thunderWar = new ThunderWar();
//        new Control(thunderWar);
    }
}
