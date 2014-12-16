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
package Juego.Interfaz;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame{



    //Botones Iniciales del menu
    JPanel panelContButIni;
    JButton buttonArcade;
    JButton buttonMultiJugador;
    JButton buttonOpciones;

    //Botones de las Opciones del juego
    JPanel panelContButOpciones;
    JButton buttonNaveApariencia;
    JButton buttonSonido;
    JButton buttonPerfilUsuario;
    JButton buttonRegresarOpciones;

    public Menu(){
        super("Thunder War");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(pantalla.width / 7, pantalla.height  / 7);

        FondoInicio fondoInicio = new FondoInicio("fondo.png");
        fondoInicio.setLayout(null);

        panelContButIni = new JPanel();
        panelContButIni.setLayout(new GridLayout(3, 1, 25, 25));
        panelContButIni.setBounds(700, 150, 150, 150);
        panelContButIni.setOpaque(false);
        buttonArcade = new JButton("Arcade");
        buttonMultiJugador = new JButton("Multi Jugador");
        buttonOpciones = new JButton("Opciones");
        panelContButIni.add(buttonArcade);
        panelContButIni.add(buttonMultiJugador);
        panelContButIni.add(buttonOpciones);


        panelContButOpciones = new JPanel();
        panelContButOpciones.setLayout(new GridLayout(4,1,25,25));
        panelContButOpciones.setBounds(200, 150, 180, 150);
        panelContButOpciones.setOpaque(false);
        buttonPerfilUsuario = new JButton("Perfil de usuario");
        buttonSonido = new JButton("Sonido ON");
        buttonNaveApariencia = new JButton("Apariencia Nuevo");
        buttonRegresarOpciones = new JButton("Regresar");
        panelContButOpciones.add(buttonPerfilUsuario);
        panelContButOpciones.add(buttonSonido);
        panelContButOpciones.add(buttonNaveApariencia);
        panelContButOpciones.setVisible(false);





        fondoInicio.add(panelContButIni);
        fondoInicio.add(panelContButOpciones);

        add(fondoInicio);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(945, 531);
        setResizable(false);
//        setVisible(true);
    }
}
