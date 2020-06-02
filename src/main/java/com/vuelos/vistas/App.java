package com.vuelos.vistas;

import com.vuelos.modelo.*;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Clase perteneciente a la ventana principal del protecto
 * @author mfuhr
 *
 */

public class App extends JFrame {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    private DecimalFormat df = new DecimalFormat("#0.00");


    public Container getPanelMain() {
        return panelMain;
    }


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
    private JLabel tiempoTotal;
    private JFormattedTextField estadiaTotal;
    private JButton calcularBtn;
    private JButton limpiarBtn;
    private JLabel labelPax;
    private JLabel labelTotalPax;
    private JLabel precioTotalMovimiento;
    private JLabel paxTotal;
    private JLabel precioTotalPesos;
    private JLabel precioTotalDolares;
    private JLabel labelCotizacion;
    private JTextField cambioMoneda;
    private JLabel tasaAterrizaje;
    private JLabel tasaEstacionamiento;
    private JLabel tasaFueraHorario;
    private JLabel tasaInvernal;
    private JLabel tasaBalizamiento;
    private JButton tarifasBtn;
    private JButton balizaBtn;
    private JLabel subTotalAterrizaje;
    private JLabel subTotalBalizamiento;
    private JLabel subTotalEstacionamiento;
    private String procedencia;
    private String destino;


    public App() throws JAXBException {

        cargarDatos();

        /**
         * Acción de boton "Calcular"
         */
        calcularBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!cambioMoneda.getText().equals("0,00")) {
                    if (!pesoVuelo.getText().isEmpty()
                            && !cambioMoneda.getText().isEmpty()
                            && !nroPax.getText().isEmpty()) {
                        crearMovimiento();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "¡Debe completar peso de la aeronave y cotización del dolar!","Atención",
                                JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("")));
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "¡La cotización no puede ser 0!","Atención",
                            JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("")));
                }
            }


        });


        /**
         * Acción de boton "Limpiar todo"
         */
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
                cambioMoneda.setText("63,25");
                precioTotalMovimiento.setText("");
                paxTotal.setText("");
                precioTotalPesos.setText("");
                precioTotalDolares.setText("");
                tasaAterrizaje.setText("0,00");
                tasaEstacionamiento.setText("0,00");
                tasaInvernal.setText("0,00");
                tasaBalizamiento.setText("0,00");
                tasaFueraHorario.setText("0,00");
            }
        });


        /**
         * Boton selector procedencia "cabotaje"
         */
        procCabotajeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProcedencia("cabotaje");
                procInternacionalBtn.setSelected(false);
                procCabotajeBtn.setSelected(true);
            }
        });

        /**
         * Boton selector procedencia "internacional"
         */
        procInternacionalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProcedencia("internacional");
                procCabotajeBtn.setSelected(false);
                procInternacionalBtn.setSelected(true);
            }
        });

        /**
         * boton selector destino "cabotaje"
         */
        destCabotajeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDestino("cabotaje");
                destInternacionlBtn.setSelected(false);
                destCabotajeBtn.setSelected(true);

            }
        });

        /**
         * boton selector destino "internacional"
         */
        destInternacionlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDestino("internacional");
                destCabotajeBtn.setSelected(false);
                destInternacionlBtn.setSelected(true);
            }
        });

        /**
         * Cambiar caracter "." a "," en tipo de cambio
         */
        cambioMoneda.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!cambioMoneda.getText().isEmpty()){
                double moneda = parseDouble(cambioMoneda.getText().replace(",","."));
                cambioMoneda.setText(df.format(moneda).replace(".",","));
                }
            }
        });

        /**
         * Completado automatico de ingreso de horas
         */
        horaArribo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

                String timeArribo = horaArribo.getText();
                if (timeArribo.length() == 2) {
                    horaArribo.setText(timeArribo + ":00");
                }else if(timeArribo.length() == 3) {
                    horaArribo.setText(timeArribo + "00");
                }else if(timeArribo.length() == 4) {
                    horaArribo.setText(timeArribo + "0");
                }
            }
        });

        /**
         * Completado automatico de ingreso de horas
         */
        horaDespegue.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String timeDespegue = horaDespegue.getText();
                if (timeDespegue.length() == 2) {
                    horaDespegue.setText(timeDespegue + ":00");
                }else if(timeDespegue.length() == 3) {
                    horaDespegue.setText(timeDespegue + "00");
                }else if(timeDespegue.length() == 4) {
                    horaDespegue.setText(timeDespegue + "0");
                }
                try {
                    estadiaTotal.setText(String.valueOf((crearEstadia().totalHoras())));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        /**
         * Completado automatico de barras en fechas
         */
        fechaArribo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String dateArribo = fechaArribo.getText();
                int tamaño = dateArribo.length();
                if (tamaño == 8) {
                    String dia = dateArribo.substring(tamaño -8, tamaño -6);
                    String mes = dateArribo.substring(tamaño -6, tamaño -4);
                    String año = dateArribo.substring(tamaño -4, tamaño);
                    fechaArribo.setText(dia + "/" + mes + "/" + año);
                }
            }
        });

        /**
         * Completado automatico de barras en fechas
         */
        fechaDespegue.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String dateDespegue = fechaDespegue.getText();
                int tamaño = dateDespegue.length();
                if (tamaño == 8) {
                    String dia = dateDespegue.substring(tamaño -8, tamaño -6);
                    String mes = dateDespegue.substring(tamaño -6, tamaño -4);
                    String año = dateDespegue.substring(tamaño -4, tamaño);
                    fechaDespegue.setText(dia + "/" + mes + "/" + año);
                }
            }
        });

        /**
         * Asigna "0" si el valor del campo de texto es nulo
         */
        nroPax.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(nroPax.getText().isEmpty()){
                    nroPax.setText("0");
                }
            }
        });

        /**
         * Abre ventana de tarifario general
         */
        tarifasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventanaTarifario = new JFrame("Tarifario General");
                try {
                    ventanaTarifario.setContentPane(new Tarifario().getPanelMain());
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
                ventanaTarifario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ventanaTarifario.pack();
                ventanaTarifario.setLocationRelativeTo(null);
                ventanaTarifario.setVisible(true);
            }

        });

        /**
         * Abre ventana de horarios y fechas de salidas y puestas de sol
         */
        balizaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Balizamiento balizamiento = new Balizamiento();
                balizamiento.mostrarVentana();

            }
        });
    }


    /**
     * Carga de datos al mostrar ventana Principal
     */
    public void cargarDatos() {

        Estadia estadia = new Estadia();
        setProcedencia("cabotaje");
        setDestino("cabotaje");
        nroVuelo.setText("####");
        matVuelo.setText("##-###");
        pesoVuelo.setText("1");
        nroPax.setText("0");
        cambioMoneda.setText("63,25");
        fechaArribo.setText(estadia.mostrarFechaHoy());
        fechaDespegue.setText(estadia.mostrarFechaHoy());
        horaArribo.setText(estadia.mostrarHoraActual());
        horaDespegue.setText(estadia.mostrarHoraActual());
        procCabotajeBtn.setSelected(true);
        destCabotajeBtn.setSelected(true);
        Persistencia.cargarAterrizaje();
        Persistencia.cargarEstacionamiento();
        Persistencia.cargarValoresPax();

    }

    /**
     * Crea una nueva estadia del vuelo cargado
     * @return Estadia del Vuelo
     * @throws ParseException Error en formato de hora o fecha
     */
    private Estadia crearEstadia() throws ParseException {
        Date dateArribo = simpleDateFormat.parse(fechaArribo.getText());
        Date dateDespegue = simpleDateFormat.parse(fechaDespegue.getText());
        Date timeArribo = simpleTimeFormat.parse(horaArribo.getText());
        Date timeDespegue = simpleTimeFormat.parse(horaDespegue.getText());
        Estadia estadia = new Estadia(dateArribo, dateDespegue, timeArribo, timeDespegue);

        return estadia;
    }

    /**
     * Crea nuevo movimiento de vuelo
     */
    void crearMovimiento() {
        try {
            crearEstadia();
            try {

                Vuelo vuelo = new Vuelo(nroVuelo.getText(), matVuelo.getText(),
                        parseInt(nroPax.getText()), parseInt(pesoVuelo.getText()), getProcedencia(), getDestino(), crearEstadia());

                TasaAterrizaje aterrizaje = new TasaAterrizaje(vuelo);
                TasaEstacionamiento estacionamiento = new TasaEstacionamiento(vuelo);

                System.out.println("Se asigno Valor Pax Cabotaje");
                vuelo.setPaxCab(Persistencia.cargarValoresPax().getPaxCab());

                System.out.println("Se asigno Valor Pax Internacional");
                vuelo.setPaxInter(Persistencia.cargarValoresPax().getPaxInter());

                estadiaTotal.setText(String.valueOf((crearEstadia().totalHoras())));
                Movimiento movimiento = new Movimiento(vuelo, crearEstadia(),parseDouble(cambioMoneda.getText().replace(",", ".")));

                movimiento.calcularCostos();

                precioTotalMovimiento.setText(movimiento.getMostrarPrecio());
                paxTotal.setText(movimiento.getMostrarCostoPax());

                precioTotalPesos.setText(movimiento.getMostrarPrecioPesos());
                precioTotalDolares.setText(movimiento.getMostrarPrecioDolares());

                tasaAterrizaje.setText(df.format(aterrizaje.getTasa()));
                tasaFueraHorario.setText(df.format(aterrizaje.getFueraHorario()));
                tasaEstacionamiento.setText(df.format(movimiento.getTasa()));
                tasaInvernal.setText(df.format(aterrizaje.getLabelTasaInvernal()));

                subTotalAterrizaje.setText(df.format(aterrizaje.getSubTotal()));
                tasaBalizamiento.setText(df.format(aterrizaje.getTasaBalizamiento())+"%");
                subTotalBalizamiento.setText(df.format((aterrizaje.getSubTotal() * aterrizaje.getTasaBalizamiento())- aterrizaje.getSubTotal()));
                subTotalEstacionamiento.setText(df.format(movimiento.getSubTotalCostoEstacionamiento()));


            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,
                        "¡El nro de pasajeros no puede contener decimales!","Atención",
                        JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("")));
            }

        }catch (ParseException | JAXBException ex) {
            JOptionPane.showMessageDialog(null,
                    "¡Ingrese el formato 'DD/MM/AAAA' para la fecha y 'HH:MM' para la hora!","Error",
                    JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("")));
        }
    }

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

}
