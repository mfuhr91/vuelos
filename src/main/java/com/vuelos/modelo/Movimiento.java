package com.vuelos.modelo;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Clase perteneciente al objeto movimiento
 */
public class Movimiento {

    private Vuelo vuelo;
    private Estadia estadia;
    private double cambioMoneda;
    private double precio;
    private double enPesos;
    private double enDolares;
    private String mostrarPrecio;
    private double costoPax;
    private String mostrarCostoPax;
    private String mostrarPrecioDolares;
    private String mostrarPrecioPesos;
    private double tasa;
    private double subTotalCostoAterrizaje;
    private double subTotalCostoEstacionamiento;



    public Movimiento(Vuelo vuelo, Estadia estadia, double cambioMoneda) {
        this.vuelo = vuelo;
        this.estadia = estadia;
        this.cambioMoneda = cambioMoneda;
    }

    /**
     * Calcula costos de movimiento de aeronave
     * @throws ParseException
     */
    public void calcularCostos() throws ParseException {

        DecimalFormat df = new DecimalFormat("0.00");
        if (vuelo.getProcedencia().equals("cabotaje") && vuelo.getDestino().equals("cabotaje")){ // PAX y MOV en pesos

            costoPistaCab();

            costoPax = vuelo.getNroPax() * vuelo.getPaxCab();
            enPesos = precio + costoPax;
            enDolares = enPesos / cambioMoneda;
            mostrarPrecio = ("AR$" + df.format(precio));
            mostrarCostoPax = ("AR$" + df.format(costoPax));
            mostrarPrecioPesos = ("AR$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));

        } else if (vuelo.getProcedencia().equals("internacional") && vuelo.getDestino().equals("cabotaje")) { //PAX pesos - MOV dolares

            costoPistaInter();

            costoPax = vuelo.getNroPax() * vuelo.getPaxCab();
            enDolares = precio + (costoPax / cambioMoneda);
            enPesos = (precio * cambioMoneda) + costoPax;
            mostrarPrecio = ("USD" + df.format(precio));
            mostrarCostoPax = ("AR$" + df.format(costoPax));
            mostrarPrecioPesos = ("AR$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));

        } else if (vuelo.getProcedencia().equals("cabotaje") && vuelo.getDestino().equals("internacional")) { // PAX dolares - MOV pesos

            costoPistaCab();

            costoPax = vuelo.getNroPax()  * vuelo.getPaxInter();
            enDolares =  (precio/cambioMoneda) + costoPax;
            enPesos = precio + (costoPax*cambioMoneda);
            mostrarPrecio = ("AR$" + df.format(precio));
            mostrarCostoPax = ("USD" + df.format(costoPax));
            mostrarPrecioPesos = ("AR$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));

        } else if (vuelo.getProcedencia().equals("internacional") && vuelo.getDestino().equals("internacional")) { //PAX dolares - MOV dolares

            costoPistaInter();

            costoPax = vuelo.getNroPax() * vuelo.getPaxInter();
            enDolares =  precio + costoPax;
            enPesos = enDolares*cambioMoneda;
            mostrarPrecio = ("USD" + df.format(precio));
            mostrarCostoPax = ("USD" + df.format(costoPax));
            mostrarPrecioPesos = ("AR$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));
        }
    }

    /**
     * Calcula Costo de Estacionamiento de vuelo con procedencia cabotaje
     * @throws ParseException
     */
    public void costoPistaCab() throws ParseException {
        System.out.println("Se carga estacionamiento en nuevo Movimiento");
        TasaEstacionamiento estacionamiento = Persistencia.cargarEstacionamiento();
        if((vuelo.getCostoEstacionamiento() * estadia.totalHoras()) <= estacionamiento.getTasaMinimaCab()){
            precio = vuelo.getCostoAterrizaje() + (estacionamiento.getTasaMinimaCab());
            setTasa(estacionamiento.getTasaMinimaCab());
            estacionamiento.setTasa(getTasa());
            setSubTotalCostoAterrizaje(vuelo.getCostoAterrizaje());
            setSubTotalCostoEstacionamiento(estacionamiento.getTasaMinimaCab());
        }else {
            precio = vuelo.getCostoAterrizaje() + (vuelo.getCostoEstacionamiento() * estadia.totalHoras());
            setSubTotalCostoAterrizaje(vuelo.getCostoAterrizaje());
            setSubTotalCostoEstacionamiento((vuelo.getCostoEstacionamiento() * estadia.totalHoras()));
            setTasa(estacionamiento.asignarCosto(vuelo));
        }
    }

    /**
     * Calcula Costo de Estacionamiento de vuelo con procedencia internacional
     * @throws ParseException
     */
    public void costoPistaInter() throws ParseException {
        System.out.println("Se carga estacionamiento en nuevo Movimiento");
        TasaEstacionamiento estacionamiento = Persistencia.cargarEstacionamiento();
        if((vuelo.getCostoEstacionamiento() * estadia.totalHoras()) <= estacionamiento.getTasaMinimaInter()){
            precio = vuelo.getCostoAterrizaje() + (estacionamiento.getTasaMinimaInter());
            setTasa(estacionamiento.getTasaMinimaInter());
            estacionamiento.setTasa(getTasa());
            setSubTotalCostoAterrizaje(vuelo.getCostoAterrizaje());
            setSubTotalCostoEstacionamiento(estacionamiento.getTasaMinimaInter());
        }else {
            precio = vuelo.getCostoAterrizaje() + (vuelo.getCostoEstacionamiento() * estadia.totalHoras());
            setSubTotalCostoAterrizaje(vuelo.getCostoAterrizaje());
            setSubTotalCostoEstacionamiento((vuelo.getCostoEstacionamiento() * estadia.totalHoras()));
            setTasa(estacionamiento.asignarCosto(vuelo));
        }
    }

    public String getMostrarPrecio() {
        return mostrarPrecio;
    }

    public String getMostrarCostoPax() {
        return mostrarCostoPax;
    }

    public String getMostrarPrecioDolares() {
        return mostrarPrecioDolares;
    }

    public String getMostrarPrecioPesos() {
        return mostrarPrecioPesos;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public void setSubTotalCostoAterrizaje(double subTotalCostoAterrizaje) {
        this.subTotalCostoAterrizaje = subTotalCostoAterrizaje;
    }

    public double getSubTotalCostoEstacionamiento() {
        return subTotalCostoEstacionamiento;
    }

    public void setSubTotalCostoEstacionamiento(double subTotalCostoEstacionamiento) {
        this.subTotalCostoEstacionamiento = subTotalCostoEstacionamiento;
    }

}
