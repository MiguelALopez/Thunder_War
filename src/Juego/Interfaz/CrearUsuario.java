/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 14-dic-2014
 * Nombre del Archivo: CrearUsuario.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego.Interfaz;

import javax.swing.*;
import java.awt.*;

public class CrearUsuario extends JFrame{

    JButton buttonRegistrar;
    JButton buttonCancelar;
    JButton buttonNave;


    JTextField textFieldNickName;
    JPasswordField passwordFieldContraseña;
    JTextField textFieldNombre;
    JTextField textFieldApellido;
    JComboBox jComboBoxTipoNave;


    public CrearUsuario(){
        super("Crear Usuario");
        setLocationRelativeTo(null);

        //Inicializo los componentes
        buttonRegistrar = new JButton("Crear");
        buttonCancelar = new JButton("Cancelar");
        textFieldNickName = new JTextField();
        passwordFieldContraseña = new JPasswordField();
        textFieldNombre = new JTextField();
        textFieldApellido = new JTextField();
        jComboBoxTipoNave = new JComboBox();
        jComboBoxTipoNave.addItem("1");
        jComboBoxTipoNave.addItem("2");
        jComboBoxTipoNave.addItem("3");
        jComboBoxTipoNave.addItem("4");
        jComboBoxTipoNave.addItem("5");

//        setLayout(new BorderLayout(20,20));
        setLayout(new FlowLayout());

        JPanel panelALL = new JPanel();
        panelALL.setLayout(new BorderLayout(20, 20));

        JPanel panelDatosRegistro = new JPanel();
        panelDatosRegistro.setOpaque(false);
        panelDatosRegistro.setLayout(new GridLayout(4, 2, 15, 15));
        panelDatosRegistro.add(new JLabel("Nick Name"));
        panelDatosRegistro.add(textFieldNickName);
        panelDatosRegistro.add(new JLabel("Password"));
        panelDatosRegistro.add(passwordFieldContraseña);
        panelDatosRegistro.add(new JLabel("Nombre"));
        panelDatosRegistro.add(textFieldNombre);
        panelDatosRegistro.add(new JLabel("Apellido"));
        panelDatosRegistro.add(textFieldApellido);

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
        panelcontButAcept.add(buttonCancelar);
        panelcontButAcept.add(buttonRegistrar);

        panelALL.add(panelDatosRegistro, BorderLayout.NORTH);
        panelALL.add(panelCargarImagen, BorderLayout.CENTER);
        panelALL.add(panelcontButAcept, BorderLayout.SOUTH);

        add(panelALL);

        setSize(280, 320);
        setResizable(false);
    }

}
