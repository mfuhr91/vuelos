package com.vuelos;

import com.vuelos.modelo.Persistencia;
import com.vuelos.modelo.TasaAterrizaje;
import com.vuelos.modelo.TasaEstacionamiento;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Valores extends JFrame{


    TasaAterrizaje aterrizaje = new TasaAterrizaje();
    TasaEstacionamiento estacionamiento = new TasaEstacionamiento();

    public Valores() throws JAXBException {
        cargarDatos();


        actualizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,"¿Está seguro que quiere realizar los cambios?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(confirm == 0){ // Yes en Ventana Emergente
                    try {
                        actualizarDatos();
                    } catch (JAXBException ex) {
                        ex.printStackTrace();
                    }

                }
                System.out.println(confirm);
            }
        });
    }

    public Container getPanelMain() {return panelMain;}
    private JPanel panelMain;
    private JLabel labelTasasAterrizaje;
    private JLabel labelTasaMinima;
    private JLabel lblMenos30;
    private JLabel lblDe31a80;
    private JLabel lblDe81a170;
    private JLabel lblMas170;
    private JButton actualizarBtn;
    private JLabel lblAterrizajeInternacional;
    private JTextField tasaMinAteCab;
    private JTextField menos30AteCab;
    private JTextField de31a80AteCab;
    private JTextField de81a170AteCab;
    private JTextField mas170AteCab;
    private JTextField tasaMinAteInter;
    private JTextField menos30AteInter;
    private JTextField de31a80AteInter;
    private JTextField de81a170AteInter;
    private JTextField mas170AteInter;
    private JTextField menos5EstCab;
    private JTextField de5a80EstCab;
    private JTextField de81a170EstCab;
    private JTextField mas170EstCab;
    private JTextField menos5EstInter;
    private JTextField de5a80EstInter;
    private JTextField de81a170EstInter;
    private JTextField mas170EstInter;


    void cargarDatos() throws JAXBException {

        Persistencia.cargarDatos();
        DecimalFormat df = new DecimalFormat("#0.00");
        //CABOTAJE
        tasaMinAteCab.setText(df.format(aterrizaje.getTasaMinimaCab()));
        menos30AteCab.setText(String.valueOf(df.format(aterrizaje.getMenosDe30tnCab())));
        de31a80AteCab.setText(String.valueOf(df.format(aterrizaje.getDe31a80tnCab())));
        de81a170AteCab.setText(String.valueOf(df.format(aterrizaje.getDe81a170tnCab())));
        mas170AteCab.setText(String.valueOf(df.format(aterrizaje.getMas170tnCab())));
        //INTERNACIONAL
        tasaMinAteInter.setText(String.valueOf(df.format(aterrizaje.getTasaMinimaInter())));
        menos30AteInter.setText(String.valueOf(df.format(aterrizaje.getMenosDe30tnInter())));
        de31a80AteInter.setText(String.valueOf(df.format(aterrizaje.getDe31a80tnInter())));
        de81a170AteInter.setText(String.valueOf(df.format(aterrizaje.getDe81a170tnInter())));
        mas170AteInter.setText(String.valueOf(df.format(aterrizaje.getMas170tnInter())));

        //CABOTAJE
        menos5EstCab.setText(String.valueOf(df.format(estacionamiento.getMenos5tnCab())));
        de5a80EstCab.setText(String.valueOf(df.format(estacionamiento.getDe5a80tnCab())));
        de81a170EstCab.setText(String.valueOf(df.format(estacionamiento.getDe81a170tnCab())));
        mas170EstCab.setText(String.valueOf(df.format(estacionamiento.getMas170tnCab())));
        //INTERNACIONAL
        menos5EstInter.setText(String.valueOf(df.format(estacionamiento.getMenos5tnInter())));
        de5a80EstInter.setText(String.valueOf(df.format(estacionamiento.getDe5a80tnInter())));
        de81a170EstInter.setText(String.valueOf(df.format(estacionamiento.getDe81a170tnInter())));
        mas170EstInter.setText(String.valueOf(df.format(estacionamiento.getMas170tnInter())));

    }

    void actualizarDatos() throws JAXBException {
        //Persistencia persistencia = new Persistencia(aterrizaje, estacionamiento);
        Persistencia.guardarDatos(aterrizaje, estacionamiento);
        /*
        DecimalFormat df = new DecimalFormat("#0.00");
        //CABOTAJE
        aterrizaje.setTasaMinimaCab(parseDouble(tasaMinAteCab.getText()));
        aterrizaje.setMenosDe30tnCab(parseDouble(menos30AteCab.getText()));
        aterrizaje.setDe31a80tnCab(parseDouble(de31a80AteCab.getText()));
        aterrizaje.setDe81a170tnCab(parseDouble(de81a170AteCab.getText()));
        aterrizaje.setMas170tnCab(parseDouble(mas170AteCab.getText()));
        //INTERNACIONAL
        aterrizaje.setTasaMinimaInter(parseDouble(tasaMinAteInter.getText()));
        aterrizaje.setMenosDe30tnInter(parseDouble(menos30AteInter.getText()));
        aterrizaje.setDe31a80tnInter(parseDouble(de31a80AteInter.getText()));
        aterrizaje.setDe81a170tnInter(parseDouble(de81a170AteInter.getText()));
        aterrizaje.setMas170tnInter(parseDouble(mas170AteInter.getText()));

        //CABOTAJE
        estacionamiento.setMenos5tnCab(parseDouble(menos5EstCab.getText()));
        estacionamiento.setDe5a80tnCab(parseDouble(de5a80EstCab.getText()));
        estacionamiento.setDe81a170tnCab(parseDouble(de81a170EstCab.getText()));
        estacionamiento.setMas170tnCab(parseDouble(mas170EstCab.getText()));
        //INTERNACIONAL
        estacionamiento.setMenos5tnInter(parseDouble(menos5EstInter.getText()));
        estacionamiento.setDe5a80tnInter(parseDouble(de5a80EstInter.getText()));
        estacionamiento.setDe81a170tnInter(parseDouble(de81a170EstInter.getText()));
        estacionamiento.setMas170tnInter(parseDouble(mas170EstInter.getText()));


         */
    }
}

