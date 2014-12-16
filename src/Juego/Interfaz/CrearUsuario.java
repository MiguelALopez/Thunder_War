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

public class CrearUsuario extends JFrame{

    JButton buttonRegistrar;
    JButton buttonCancelar;

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

        setLayout(new BorderLayout(20,20));

        JPanel panelDatosRegistro = new JPanel();
        panelDatosRegistro.setLayout(new GridLayout(2,2,15,15));
        panelDatosRegistro.add(new JLabel("Nick Name"));
        panelDatosRegistro.add(textFieldNickName);
        panelDatosRegistro.add(new JLabel("Password"));
        panelDatosRegistro.add(passwordFieldContraseña);


        JPanel panelDatosPersonales = new JPanel();
        panelDatosPersonales.setLayout(new GridLayout(2,2,15,15));
        panelDatosPersonales.add(new JLabel("Nombre"));
        panelDatosPersonales.add(textFieldNombre);
        panelDatosPersonales.add(new JLabel("Apellido"));
        panelDatosPersonales.add(textFieldApellido);

        JPanel panelCargarImagen = new JPanel();
        panelCargarImagen.setLayout(new GridLayout(2, 2, 15, 15));

        JPanel panelContentInfNave = new JPanel();
        panelContentInfNave.setLayout(new BorderLayout());
        panelContentInfNave.add(new JLabel("Tipo de Nave"), BorderLayout.NORTH);
        panelContentInfNave.add(jComboBoxTipoNave,BorderLayout.CENTER);


        ImageIcon imagen = new ImageIcon(getClass().getResource("../avion.png"));
//        Image imgAvion = Toolkit.getDefaultToolkit().getImage("avion.png");
        JPanel panelImgTipoNave = new JPanel();
        JButton marcoImagen = new JButton();
        marcoImagen.setIcon(imagen);
        marcoImagen.setEnabled(true);
        panelImgTipoNave.add(marcoImagen);

//        JPanel panelContentBottom = new JPanel();
//        panelContentBottom.add(buttonRegistrar);
//        panelContentBottom.add(buttonCancelar)

        panelCargarImagen.add(panelContentInfNave);
        panelCargarImagen.add(panelImgTipoNave);
        panelCargarImagen.add(buttonCancelar);
        panelCargarImagen.add(buttonRegistrar);





        add(panelDatosRegistro, BorderLayout.NORTH);
        add(panelDatosPersonales, BorderLayout.CENTER);
        add(panelCargarImagen, BorderLayout.SOUTH);


        setSize(330, 350);
        setResizable(false);
    }

}
