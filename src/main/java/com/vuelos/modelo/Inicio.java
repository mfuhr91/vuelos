package com.vuelos.modelo;

import com.vuelos.App;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;

public class Inicio {
    public static void main(String[] args) throws JAXBException {
        JFrame ventanaApp = new JFrame("Vuelos AIUMA");
        ventanaApp.setContentPane(new App().getPanelMain());
        ventanaApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaApp.pack();
        ventanaApp.setLocationRelativeTo(null);
        ventanaApp.setVisible(true);
    }
}
