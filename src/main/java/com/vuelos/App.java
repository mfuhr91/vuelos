package com.vuelos;

import com.vuelos.modelo.*;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class App extends JFrame{

    private Container getPanelMain() {return panelMain;}


    private JLabel labelVuelo;
    private JFormattedTextField nroVuelo;
    private JFormattedTextField pesoVuelo;
    private JFormattedTextField nroPax;
    private JFormattedTextField matVuelo;
    private JPanel panelMain;
    private JRadioButton procCabotajeBtn;
    private JRadioButton procInternacionalBtn;
    private JLabel labelProcedencia;
    private JFormattedTextField fechaArribo;
    private JFormattedTextField fechaDespegue;
    private JLabel labelDestino;
    private JRadioButton destCabotajeBtn;
    private JRadioButton destInternacionlBtn;
    private JFormattedTextField horaArribo;
    private JFormattedTextField horaDespegue;
    private JLabel labelEstadia;
    private JLabel labelTotal;
    private JFormattedTextField precioTotalPesos;
    private JLabel tiempoTotal;
    private JFormattedTextField estadiaTotal;
    private JButton calcularBtn;
    private JButton limpiarBtn;
    private JLabel labelPax;
    private JFormattedTextField paxTotal;
    private JLabel labelTotalPax;
    private JFormattedTextField precioTotalDolares;
    private JFormattedTextField precioTotalMovimiento;
    private JLabel labelCotizacion;
    private JTextField cambioMoneda;
    private JLabel tasaAterrizaje;
    private JLabel tasaEstacionamiento;
    private JLabel tasaFueraHorario;
    private JLabel tasaInvernal;
    private JLabel tasaBalizamiento;
    private JButton valoresBtn;
    //private JTextField cambioMoneda;
    private String procedencia;
    private String destino;

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public static void main(String[] args) throws JAXBException {

        JFrame ventanaApp = new JFrame(" --- Vuelos AIUMA --- ");
        ventanaApp.setContentPane(new App().getPanelMain());
        ventanaApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaApp.pack();
        ventanaApp.setLocationRelativeTo(null);
        ventanaApp.setVisible(true);

    }



    public App() throws JAXBException {

        cargarDatos();
        Persistencia.cargarDatos();
        TasaAterrizaje ate = new TasaAterrizaje();
        System.out.println(ate.getTasaMinimaCab());

        calcularBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!cambioMoneda.getText().contains("0")) {
                    if(!pesoVuelo.getText().isEmpty()
                            && !cambioMoneda.getText().isEmpty()
                            && !nroPax.getText().isEmpty()) {

                        crearVueloSinPax();

                    }else if(!pesoVuelo.getText().isEmpty() && !cambioMoneda.getText().isEmpty()){

                        crearVueloConPax();

                    }else{
                        JOptionPane.showMessageDialog(null,"¡Debe completar peso de la aeronave, y cotización del dolar!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "¡La cotización no puede ser 0!");
                }
            }



        });


        limpiarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estadia estadia = new Estadia();
                nroVuelo.setText("####");
                matVuelo.setText("##-###");
                pesoVuelo.setText("1");
                nroPax.setText("0");
                fechaArribo.setText(estadia.mostrarFechaHoy());
                fechaDespegue.setText(estadia.mostrarFechaHoy());
                horaArribo.setText(estadia.mostrarHoraActual());
                horaDespegue.setText(estadia.mostrarHoraActual());
                estadiaTotal.setText("");
                cambioMoneda.setText("63.25");
                precioTotalMovimiento.setText("");
                paxTotal.setText("");
                precioTotalPesos.setText("");
                precioTotalDolares.setText("");
                tasaAterrizaje.setText("0");
                tasaEstacionamiento.setText("0");
                tasaInvernal.setText("0");
                tasaBalizamiento.setText("0");
                tasaFueraHorario.setText("0");
            }
        });


        procCabotajeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProcedencia("cabotaje");
                procInternacionalBtn.setSelected(false);
            }
        });
        procInternacionalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProcedencia("internacional");
                procCabotajeBtn.setSelected(false);
            }
        });

        destCabotajeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDestino("cabotaje");
                destInternacionlBtn.setSelected(false);

            }
        });
        destInternacionlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDestino("internacional");
                destCabotajeBtn.setSelected(false);
            }
        });

        valoresBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventanaValores = new JFrame(" --- Valores Generales --- ");
                try {
                    ventanaValores.setContentPane(new Valores().getPanelMain());
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
                ventanaValores.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                ventanaValores.pack();
                ventanaValores.setLocationRelativeTo(null);
                ventanaValores.setVisible(true);

            }
        });
    }



    public void cargarDatos(){
        Estadia estadia = new Estadia();
        setProcedencia("cabotaje");
        setDestino("cabotaje");
        nroVuelo.setText("####");
        matVuelo.setText("##-###");
        pesoVuelo.setText("1");
        nroPax.setText("0");
        cambioMoneda.setText("63.25");
        fechaArribo.setText(estadia.mostrarFechaHoy());
        fechaDespegue.setText(estadia.mostrarFechaHoy());
        horaArribo.setText(estadia.mostrarHoraActual());
        horaDespegue.setText(estadia.mostrarHoraActual());
        procCabotajeBtn.setSelected(true);
        destCabotajeBtn.setSelected(true);
    }

    public void crearVueloSinPax(){
        DecimalFormat df = new DecimalFormat("#0.00");
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");

            Date dateArribo = simpleDateFormat.parse(fechaArribo.getText());
            Date dateDespegue = simpleDateFormat.parse(fechaDespegue.getText());
            Date timeArribo = simpleTimeFormat.parse(horaArribo.getText());
            Date timeDespegue = simpleTimeFormat.parse(horaDespegue.getText());

            Estadia estadia = new Estadia(dateArribo, dateDespegue, timeArribo, timeDespegue);

            Vuelo vuelo = new Vuelo(nroVuelo.getText(), matVuelo.getText(),
                    parseInt(nroPax.getText()), parseInt(pesoVuelo.getText()),
                    getProcedencia(), getDestino(), estadia);


            TasaAterrizaje aterrizaje = new TasaAterrizaje(vuelo);
            TasaEstacionamiento estacionamiento = new TasaEstacionamiento(vuelo);

            tasaAterrizaje.setText(df.format(aterrizaje.getTasa()));
            tasaEstacionamiento.setText(df.format(estacionamiento.getTasa()));
            tasaFueraHorario.setText(df.format(aterrizaje.getFueraHorario()));

            estadiaTotal.setText(String.valueOf((estadia.totalHoras())));

            Movimiento movimiento = new Movimiento(vuelo, estadia, aterrizaje,
                    estacionamiento, parseDouble(cambioMoneda.getText()));

            movimiento.calcularCostos();




            precioTotalMovimiento.setText(movimiento.getMostrarPrecio());
            paxTotal.setText(movimiento.getMostrarCostoPax());

            precioTotalPesos.setText(movimiento.getMostrarPrecioPesos());
            precioTotalDolares.setText(movimiento.getMostrarPrecioDolares());

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese una fecha y hora con formato DD/MM/AAAA HH:MM");
        }
    }

    void crearVueloConPax(){
        DecimalFormat df = new DecimalFormat("#0.00");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
            Date dateArribo = simpleDateFormat.parse(fechaArribo.getText());
            Date dateDespegue = simpleDateFormat.parse(fechaDespegue.getText());
            Date timeArribo = simpleTimeFormat.parse(horaArribo.getText());
            Date timeDespegue = simpleTimeFormat.parse(horaDespegue.getText());
            Estadia estadia = new Estadia(dateArribo, dateDespegue, timeArribo, timeDespegue);


            Vuelo vuelo = new Vuelo(nroVuelo.getText(), matVuelo.getText(), parseInt(pesoVuelo.getText()), getProcedencia(), getDestino(), estadia);

            TasaAterrizaje aterrizaje = new TasaAterrizaje(vuelo);
            TasaEstacionamiento estacionamiento = new TasaEstacionamiento(vuelo);

            tasaAterrizaje.setText(df.format(aterrizaje.getTasa()));
            tasaEstacionamiento.setText(df.format(estacionamiento.getTasa()));
            tasaFueraHorario.setText(df.format(aterrizaje.getFueraHorario()));

            estadiaTotal.setText(String.valueOf((estadia.totalHoras())));
            Movimiento movimiento = new Movimiento(vuelo, estadia, aterrizaje,
                    estacionamiento, parseDouble(cambioMoneda.getText()));

            movimiento.calcularCostos();

            precioTotalMovimiento.setText(movimiento.getMostrarPrecio());
            paxTotal.setText(movimiento.getMostrarCostoPax());

            precioTotalPesos.setText(movimiento.getMostrarPrecioPesos());
            precioTotalDolares.setText(movimiento.getMostrarPrecioDolares());


        }catch (ParseException ex){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha y hora con formato DD/MM/AAAA HH:MM");
        }
    }




}
