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

public class Autentificar extends JFrame {
//    ManejadorEvento Evento = new ManejadorEvento();
    public JTextField textNomUsuario;
    public JPasswordField passwordFieldUsuario;
    public JButton buttonIniciar;
    public JButton buttonRegistrarse;


    GridLayout GL = new GridLayout(3, 2);
    Container Contenedor = new Container();

    public Autentificar() {
        super("Iniciar Sesion");
        setLocationRelativeTo(null);

        textNomUsuario = new JTextField();
        passwordFieldUsuario = new JPasswordField();
        buttonIniciar = new JButton("Iniciar");
        buttonRegistrarse = new JButton("Registrarse");



        Contenedor = getContentPane();
        Contenedor.setLayout(new GridLayout(3, 2, 2, 2));
        Contenedor.add(new JLabel("Usuario: "));
        Contenedor.add(textNomUsuario);
        Contenedor.add(new JLabel("Password: "));
        Contenedor.add(passwordFieldUsuario);
        Contenedor.add(buttonRegistrarse);
        Contenedor.add(buttonIniciar);
        buttonIniciar.requestFocus();


//        buttonIniciar.addActionListener(Evento);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(250, 100);
        setVisible(true);
    }

    /*public static void main(String[] args) {
        new Autentificar();
    }*/

}
