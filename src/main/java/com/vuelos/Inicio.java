package com.vuelos;

import com.vuelos.vistas.App;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.util.function.IntConsumer;

/**
 * Clase de Inicio de la App,
 * creación de la ventana principal
 */
public class Inicio {
    public static void main(String[] args) throws JAXBException {

        JFrame ventanaApp = new JFrame("Vuelos AIUMA");
        ventanaApp.setIconImage(new ImageIcon(Inicio.class.getResource("/plane.png")).getImage());
        ventanaApp.setContentPane(new App().getPanelMain());
        ventanaApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaApp.pack();
        ventanaApp.setLocationRelativeTo(null);
        ventanaApp.setVisible(true);

    }

 }

