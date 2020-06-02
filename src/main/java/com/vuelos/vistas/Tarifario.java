package com.vuelos.vistas;

import com.vuelos.modelo.Persistencia;
import com.vuelos.modelo.TasaAterrizaje;
import com.vuelos.modelo.TasaEstacionamiento;
import com.vuelos.modelo.Vuelo;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Double.parseDouble;

/**
 * Clase que pertenece a la ventana Tarifario General
 */
public class Tarifario extends JFrame {

    public Container getPanelMain() {
        return panelMain;
    }
    private SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
    private JTextField fueraHorario;
    private JTextField paxCab;
    private JTextField paxInter;
    private JTextField tasaBalizamiento;
    private JFormattedTextField horaApertura;
    private JFormattedTextField horaCierre;
    private JTextField inviernoDesde;
    private JTextField inviernoHasta;
    private JTextField tasaInviernoCab;
    private JTextField tasaInviernoInter;
    private DecimalFormat df = new DecimalFormat("0.00");

    public Tarifario() throws JAXBException {

        cargarDatos();
        actualizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere realizar los cambios?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                        new ImageIcon(getClass().getResource("/alerta.png")));
                if (confirm == 0) { // Yes en Ventana Emergente
                    try {
                        actualizarDatos();
                        JOptionPane.showMessageDialog(null,
                                "¡Se actualizaron los datos correctamente!","Confirmación",
                                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/ok.png")));
                    } catch (JAXBException | ParseException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    try {
                        cargarDatos();
                    } catch (JAXBException ex) {
                        ex.printStackTrace();
                    }
                }
            }


        });

        inviernoDesde.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String fechaDesde = inviernoDesde.getText();
                int tamaño = fechaDesde.length();
                if (tamaño == 8) {
                    String dia = fechaDesde.substring(tamaño -8, tamaño -6);
                    String mes = fechaDesde.substring(tamaño -6, tamaño -4);
                    String año = fechaDesde.substring(tamaño -4, tamaño);
                    inviernoDesde.setText(dia + "/" + mes + "/" + año);
                }
            }
        });
        inviernoHasta.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String fechaHasta = inviernoHasta.getText();
                int tamaño = fechaHasta.length();
                if (tamaño == 8) {
                    String dia = fechaHasta.substring(tamaño -8, tamaño -6);
                    String mes = fechaHasta.substring(tamaño -6, tamaño -4);
                    String año = fechaHasta.substring(tamaño -4, tamaño);
                    inviernoHasta.setText(dia + "/" + mes + "/" + año);
                }
            }
        });
    }


    /**
     * metodo que se encarga de cargar los datos de la ventana
     * @throws JAXBException
     */
    void cargarDatos() throws JAXBException {

        TasaAterrizaje aterrizaje = Persistencia.cargarAterrizaje();
        TasaEstacionamiento estacionamiento = Persistencia.cargarEstacionamiento();
        Vuelo vuelo = Persistencia.cargarValoresPax();


        //CABOTAJE
        tasaMinAteCab.setText(df.format(aterrizaje.getTasaMinimaCab()));
        menos30AteCab.setText(df.format(aterrizaje.getMenosDe30tnCab()));
        de31a80AteCab.setText(df.format(aterrizaje.getDe31a80tnCab()));
        de81a170AteCab.setText(df.format(aterrizaje.getDe81a170tnCab()));
        mas170AteCab.setText(df.format(aterrizaje.getMas170tnCab()));
        tasaInviernoCab.setText(df.format(aterrizaje.getTasaInviernoCab()));
        //INTERNACIONAL
        tasaMinAteInter.setText(df.format(aterrizaje.getTasaMinimaInter()));
        menos30AteInter.setText(df.format(aterrizaje.getMenosDe30tnInter()));
        de31a80AteInter.setText(df.format(aterrizaje.getDe31a80tnInter()));
        de81a170AteInter.setText(df.format(aterrizaje.getDe81a170tnInter()));
        mas170AteInter.setText(df.format(aterrizaje.getMas170tnInter()));
        tasaInviernoInter.setText(df.format(aterrizaje.getTasaInviernoInter()));

        //CABOTAJE
        menos5EstCab.setText(df.format(estacionamiento.getTasaMinimaCab()));
        de5a80EstCab.setText(df.format(estacionamiento.getMenos80tnCab()));
        de81a170EstCab.setText(df.format(estacionamiento.getDe81a170tnCab()));
        mas170EstCab.setText(df.format(estacionamiento.getMas170tnCab()));
        //INTERNACIONAL
        menos5EstInter.setText(df.format(estacionamiento.getTasaMinimaInter()));
        de5a80EstInter.setText(df.format(estacionamiento.getMenos80tnInter()));
        de81a170EstInter.setText(df.format(estacionamiento.getDe81a170tnInter()));
        mas170EstInter.setText(df.format(estacionamiento.getMas170tnInter()));


        fueraHorario.setText(df.format(aterrizaje.getFueraHorario()));
        horaApertura.setText(simpleTimeFormat.format(aterrizaje.getHoraApertura()));
        horaCierre.setText(simpleTimeFormat.format(aterrizaje.getHoraCierre()));
        tasaBalizamiento.setText(df.format(aterrizaje.getTasaBalizamiento()));
        inviernoDesde.setText(simpleDateFormat.format(aterrizaje.getInviernoDesde()));
        inviernoHasta.setText(simpleDateFormat.format(aterrizaje.getInviernoHasta()));

        paxInter.setText(df.format(vuelo.getPaxInter()));
        paxCab.setText(df.format(vuelo.getPaxCab()));

    }

    /**
     * metodo que se encarga de actualizar y guardar los datos.
     * @throws JAXBException
     * @throws ParseException
     */
    void actualizarDatos() throws JAXBException, ParseException {
        TasaAterrizaje aterrizaje = new TasaAterrizaje();
        TasaEstacionamiento estacionamiento = new TasaEstacionamiento();
        Vuelo vuelo = new Vuelo();

        //CABOTAJE
        aterrizaje.setTasaMinimaCab(parseDouble(tasaMinAteCab.getText().replace(",", ".")));
        aterrizaje.setMenosDe30tnCab(parseDouble(menos30AteCab.getText().replace(",", ".")));
        aterrizaje.setDe31a80tnCab(parseDouble(de31a80AteCab.getText().replace(",", ".")));
        aterrizaje.setDe81a170tnCab(parseDouble(de81a170AteCab.getText().replace(",", ".")));
        aterrizaje.setMas170tnCab(parseDouble(mas170AteCab.getText().replace(",", ".")));
        aterrizaje.setTasaInviernoCab(parseDouble(tasaInviernoCab.getText().replace(",",".")));
        //INTERNACIONAL
        aterrizaje.setTasaMinimaInter(parseDouble(tasaMinAteInter.getText().replace(",", ".")));
        aterrizaje.setMenosDe30tnInter(parseDouble(menos30AteInter.getText().replace(",", ".")));
        aterrizaje.setDe31a80tnInter(parseDouble(de31a80AteInter.getText().replace(",", ".")));
        aterrizaje.setDe81a170tnInter(parseDouble(de81a170AteInter.getText().replace(",", ".")));
        aterrizaje.setMas170tnInter(parseDouble(mas170AteInter.getText().replace(",", ".")));
        aterrizaje.setTasaInviernoInter(parseDouble(tasaInviernoInter.getText().replace(",",".")));

        //CABOTAJE
        estacionamiento.setTasaMinimaCab(parseDouble(menos5EstCab.getText().replace(",", ".")));
        estacionamiento.setMenos80tnCab(parseDouble(de5a80EstCab.getText().replace(",", ".")));
        estacionamiento.setDe81a170tnCab(parseDouble(de81a170EstCab.getText().replace(",", ".")));
        estacionamiento.setMas170tnCab(parseDouble(mas170EstCab.getText().replace(",", ".")));
        //INTERNACIONAL
        estacionamiento.setTasaMinimaInter(parseDouble(menos5EstInter.getText().replace(",", ".")));
        estacionamiento.setMenos80tnInter(parseDouble(de5a80EstInter.getText().replace(",", ".")));
        estacionamiento.setDe81a170tnInter(parseDouble(de81a170EstInter.getText().replace(",", ".")));
        estacionamiento.setMas170tnInter(parseDouble(mas170EstInter.getText().replace(",", ".")));


        aterrizaje.setFueraHorario(parseDouble(fueraHorario.getText().replace(",", ".")));
        aterrizaje.setHoraApertura(simpleTimeFormat.parse(horaApertura.getText()));
        aterrizaje.setHoraCierre(simpleTimeFormat.parse(horaCierre.getText()));
        aterrizaje.setTasaBalizamiento(parseDouble(tasaBalizamiento.getText().replace(",", ".")));
        aterrizaje.setInviernoDesde(simpleDateFormat.parse(inviernoDesde.getText()));
        aterrizaje.setInviernoHasta(simpleDateFormat.parse(inviernoHasta.getText()));

        vuelo.setPaxCab(parseDouble(paxCab.getText().replace(",",".")));
        vuelo.setPaxInter(parseDouble(paxInter.getText().replace(",",".")));

        Persistencia.guardarDatos(aterrizaje, estacionamiento, vuelo);

    }

}

