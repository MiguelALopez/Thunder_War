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

public class Menu extends JFrame {


    //Panel con el menu inicial del juego
    JPanel panelContButIni;
    JButton buttonArcade;
    JButton buttonMultiJugador;
    JButton buttonOpciones;

    //Panel con las opciones del juego
    JPanel panelContButOpciones;
    JButton buttonGuardar;
    JPasswordField passwordFieldContraseña;
    JTextField textFieldNombre;
    JTextField textFieldApellido;
    JComboBox jComboBoxTipoNave;
    JButton buttonSonido;
    JButton buttonNave;

    //Panel con el inicio del modo arcade
    JPanel panelContArcadeIni;
    JComboBox jComboBoxNivel;
    JSpinner jSpinnerTiempo;
    JButton buttonIniciarJuego;



    public Menu() {
        super("Thunder War");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(pantalla.width / 7, pantalla.height / 7);

        FondoInicio fondoInicio = new FondoInicio("../Imagenes/fondo.png");
        fondoInicio.setLayout(null);

        //Menu inicial
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



        //Menu de Opciones
        panelContButOpciones = new JPanel();
        panelContButOpciones.setBounds(170, 100, 230, 290);
        panelContButOpciones.setOpaque(false);
        panelContButOpciones.setLayout(new BorderLayout(20 ,20));

        //Inicializamos los elementos de opciones
        buttonGuardar = new JButton("Guardar");
        passwordFieldContraseña = new JPasswordField();
        textFieldNombre = new JTextField();
        textFieldApellido = new JTextField();
        jComboBoxTipoNave = new JComboBox();
        jComboBoxTipoNave.addItem("1");
        jComboBoxTipoNave.addItem("2");
        jComboBoxTipoNave.addItem("3");
        jComboBoxTipoNave.addItem("4");
        jComboBoxTipoNave.addItem("5");
        buttonSonido = new JButton("ON");


        JPanel panelDatosRegistro = new JPanel();
        panelDatosRegistro.setOpaque(false);
        panelDatosRegistro.setLayout(new GridLayout(4,2,15,15));
        panelDatosRegistro.add(new JLabel("Password"));
        panelDatosRegistro.add(passwordFieldContraseña);
        panelDatosRegistro.add(new JLabel("Nombre"));
        panelDatosRegistro.add(textFieldNombre);
        panelDatosRegistro.add(new JLabel("Apellido"));
        panelDatosRegistro.add(textFieldApellido);
        panelDatosRegistro.add(new JLabel("Sonido"));
        panelDatosRegistro.add(buttonSonido);

        JPanel panelCargarImagen = new JPanel();
        panelCargarImagen.setOpaque(false);
        panelCargarImagen.setLayout(new GridLayout(1, 2, 15, 15));

        JPanel panelContentInfNave = new JPanel();
        panelContentInfNave.setOpaque(false);
        panelContentInfNave.setLayout(new BorderLayout());
        panelContentInfNave.add(new JLabel("Tipo de Nave"), BorderLayout.NORTH);
        panelContentInfNave.add(jComboBoxTipoNave,BorderLayout.CENTER);

        ImageIcon imagen = new ImageIcon(getClass().getResource("../Imagenes/avion1.png"));
        JPanel panelImgTipoNave = new JPanel();
        panelImgTipoNave.setOpaque(false);
        buttonNave = new JButton();
        buttonNave.setIcon(imagen);
        buttonNave.setEnabled(true);
        panelImgTipoNave.add(buttonNave);
        panelCargarImagen.add(panelContentInfNave);
        panelCargarImagen.add(panelImgTipoNave);


        JPanel panelcontButAcept = new JPanel();
        panelcontButAcept.setOpaque(false);
        panelcontButAcept.add(buttonGuardar);

        panelContButOpciones.add(panelDatosRegistro, BorderLayout.NORTH);
        panelContButOpciones.add(panelCargarImagen, BorderLayout.CENTER);
        panelContButOpciones.add(panelcontButAcept, BorderLayout.SOUTH);
        panelContButOpciones.setVisible(false);


        //Menu de inicio del modo arcade
        panelContArcadeIni = new JPanel();
        panelContArcadeIni.setBounds(170, 100, 150, 90);
        panelContArcadeIni.setOpaque(false);
        panelContArcadeIni.setLayout(new BorderLayout());

        //Inicializo los elementos de arcade
        jComboBoxNivel = new JComboBox();
        jComboBoxNivel.addItem("Facil");
        jComboBoxNivel.addItem("Medio");
        jComboBoxNivel.addItem("Dificil");
        jSpinnerTiempo = new JSpinner();
        jSpinnerTiempo.setValue(60);
        buttonIniciarJuego = new JButton("Iniciar");

        JPanel panelArcadeOpciones = new JPanel();
        panelArcadeOpciones.setLayout(new GridLayout(2, 2, 5, 5));
        panelArcadeOpciones.setOpaque(false);
        panelArcadeOpciones.add(new JLabel("Nivel"));
        panelArcadeOpciones.add(jComboBoxNivel);
        panelArcadeOpciones.add(new JLabel("Duracion"));
        panelArcadeOpciones.add(jSpinnerTiempo);

        panelContArcadeIni.add(panelArcadeOpciones, BorderLayout.CENTER);
        panelContArcadeIni.add(buttonIniciarJuego, BorderLayout.SOUTH);
        panelContArcadeIni.setVisible(false);

        fondoInicio.add(panelContButIni);
        fondoInicio.add(panelContButOpciones);
        fondoInicio.add(panelContArcadeIni);
        add(fondoInicio);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(945, 531);
        setResizable(false);
        setVisible(true);
    }
}
