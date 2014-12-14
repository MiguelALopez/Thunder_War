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

    public void terminarPartida() {
        pantalla.terminarPartida();
        lanzarEnemigos.stop();
    }

    //Carga datos iniciales del juego
    public void iniciarJuego(Jugador jugador){
        //Inicio añado el jugador principal a la primera posicion del arreglo
        jugadores[0] = jugador;
        //Metodo encargado de cargar las configuraciones iniciales de un usuario
//        cargarConfiguraciones();
        nivelUno();
    }

    public void arcade(){
        int invecil = 0;

    }

    public void nivelUno(){
        // envia datos a lanzar enemigo para darle un nv de dif
        jugadores[0].iniciarPartida(100, 30, 18, getWidth(), getHeight());
        enemig = new enemigo[5];
        iniciado = true;

        for (int i = 0; i < enemig.length; i++) {
            //Iniciar los enemigos
            enemig[i] = new enemigo(this);
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
