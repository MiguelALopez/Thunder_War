/***********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 14-dic-2014
 * Nombre del Archivo: ThunderWar.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * **********************************************/
package Juego;

import Juego.Interfaz.Autentificar;
import Juego.Interfaz.CrearUsuario;
import Juego.Interfaz.Eventos;
import Juego.Interfaz.Menu;

public class ThunderWar {
    public static void main(String[] args) {
        Autentificar autentificar = new Autentificar();
        Menu menu = new Menu();
        CrearUsuario crearUsuario = new CrearUsuario();
        VentanaJuego ventanaJuego= new VentanaJuego();
        new Control(ventanaJuego);
        new Eventos(autentificar, crearUsuario, menu, ventanaJuego);
    }
}
