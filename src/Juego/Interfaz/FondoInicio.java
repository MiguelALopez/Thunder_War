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

    public class FondoInicio extends JPanel{

        private Image imagen;

        public FondoInicio() {
        }

        public FondoInicio(String nombreImagen) {
            if (nombreImagen != null) {
                imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
            }
        }
        public FondoInicio(Image imagenInicial) {
            if (imagenInicial != null) {
                imagen = imagenInicial;
            }
        }

        public void setImagen(String nombreImagen) {
            if (nombreImagen != null) {
                imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
            } else {
                imagen = null;
            }

            repaint();
        }

        public void setImagen(Image nuevaImagen) {
            imagen = nuevaImagen;

            repaint();
        }
        @Override
        public void paint(Graphics g) {
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

                setOpaque(false);
            } else {

                setOpaque(true);
            }

            super.paint(g);
        }
    }
