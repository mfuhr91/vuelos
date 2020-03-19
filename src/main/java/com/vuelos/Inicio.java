package com.vuelos;

import com.vuelos.vistas.Balizamiento;

import javax.xml.bind.JAXBException;

public class Inicio {
    public static void main(String[] args) throws JAXBException {
        /*
        JFrame ventanaApp = new JFrame("Vuelos AIUMA");
        ventanaApp.setContentPane(new Baliza().getPanelMain());  //App().getPanelMain());
        ventanaApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaApp.setSize(400,400);
        ventanaApp.pack();
        ventanaApp.setLocationRelativeTo(null);
        ventanaApp.setVisible(true);

         */

        Balizamiento balizamiento = new Balizamiento();
        balizamiento.mostrarVentana();
        //balizamiento.mostrarDatos();
        //balizamiento.mostrarPares();


    }
}
