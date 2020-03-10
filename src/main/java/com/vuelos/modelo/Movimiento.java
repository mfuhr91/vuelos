package com.vuelos.modelo;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Movimiento {

    private int nroMovimiento;
    private Vuelo vuelo;
    private Estadia estadia;
    private TasaAterrizaje tasaAterrizaje;
    private TasaEstacionamiento tasaEstacionamiento;
    private double cambioMoneda;
    private double precio;
    private double enPesos;
    private double enDolares;
    private String mostrarPrecio;
    private double costoPax;
    private String mostrarCostoPax;
    private String mostrarPrecioDolares;
    private String mostrarPrecioPesos;

    public Movimiento(Vuelo vuelo, Estadia estadia, TasaAterrizaje tasaAterrizaje, TasaEstacionamiento tasaEstacionamiento, double cambioMoneda) {
        this.vuelo = vuelo;
        this.estadia = estadia;
        this.tasaAterrizaje = tasaAterrizaje;
        this.tasaEstacionamiento = tasaEstacionamiento;
        this.cambioMoneda = cambioMoneda;
    }

    // Calcula costos de movimiento
    public void calcularCostos() throws ParseException {
        DecimalFormat df = new DecimalFormat("#0.00");
        if (vuelo.getProcedencia().equals("cabotaje") && vuelo.getDestino().equals("cabotaje")){ // PAX y MOV en pesos
            costoPax = vuelo.getNroPax() * 140;
            precio = vuelo.getCostoAterrizaje() + (vuelo.getCostoEstacionamiento() * estadia.totalHoras());
            enPesos = precio + costoPax;
            enDolares =  enPesos/cambioMoneda;
            mostrarPrecio = ("$" + df.format(precio));
            mostrarCostoPax = ("$" + df.format(costoPax));
            mostrarPrecioPesos = ("$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));

        } else if (vuelo.getProcedencia().equals("internacional") && vuelo.getDestino().equals("cabotaje")) { //PAX pesos - MOV dolares
            costoPax = vuelo.getNroPax() * 140;
            precio = vuelo.getCostoAterrizaje() + (vuelo.getCostoEstacionamiento() * estadia.totalHoras());
            enDolares =  precio + (costoPax/cambioMoneda);
            enPesos = (precio*cambioMoneda) + costoPax;
            mostrarPrecio = ("USD" + df.format(precio));
            mostrarCostoPax = ("$" + df.format(costoPax));
            mostrarPrecioPesos = ("$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));

        } else if (vuelo.getProcedencia().equals("cabotaje") && vuelo.getDestino().equals("internacional")) { // PAX dolares - MOV pesos
            costoPax = vuelo.getNroPax()  * 49;
            precio = vuelo.getCostoAterrizaje() + (vuelo.getCostoEstacionamiento() * estadia.totalHoras());
            enDolares =  (precio/cambioMoneda) + costoPax;
            enPesos = precio + (costoPax*cambioMoneda);
            mostrarPrecio = ("$" + df.format(precio));
            mostrarCostoPax = ("USD" + df.format(costoPax));
            mostrarPrecioPesos = ("$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));

        } else if (vuelo.getProcedencia().equals("internacional") && vuelo.getDestino().equals("internacional")) { //PAX dolares - MOV dolares
            costoPax = vuelo.getNroPax() * 49;
            precio =  vuelo.getCostoAterrizaje() + (vuelo.getCostoEstacionamiento() * estadia.totalHoras());
            enDolares =  precio + costoPax;
            enPesos = enDolares*cambioMoneda;
            mostrarPrecio = ("USD" + df.format(precio));
            mostrarCostoPax = ("USD" + df.format(costoPax));
            mostrarPrecioPesos = ("$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));
        }
    }

    public int getNroMovimiento() {
        return nroMovimiento;
    }

    public void setNroMovimiento(int nroMovimiento) {
        this.nroMovimiento = nroMovimiento;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public TasaAterrizaje getTasaAterrizaje() {
        return tasaAterrizaje;
    }

    public void setTasaAterrizaje(TasaAterrizaje tasaAterrizaje) {
        this.tasaAterrizaje = tasaAterrizaje;
    }

    public TasaEstacionamiento getTasaEstacionamiento() {
        return tasaEstacionamiento;
    }

    public void setTasaEstacionamiento(TasaEstacionamiento tasaEstacionamiento) {
        this.tasaEstacionamiento = tasaEstacionamiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCostoPax() {
        return costoPax;
    }

    public void setCostoPax(double costoPax) {
        this.costoPax = costoPax;
    }

    public double getCambioMoneda() {
        return cambioMoneda;
    }

    public void setCambioMoneda(double cambioMoneda) {
        this.cambioMoneda = cambioMoneda;
    }

    public String getMostrarPrecio() {
        return mostrarPrecio;
    }

    public void setMostrarPrecio(String mostrarPrecio) {
        this.mostrarPrecio = mostrarPrecio;
    }

    public String getMostrarCostoPax() {
        return mostrarCostoPax;
    }

    public void setMostrarCostoPax(String mostrarCostoPax) {
        this.mostrarCostoPax = mostrarCostoPax;
    }

    public String getMostrarPrecioDolares() {
        return mostrarPrecioDolares;
    }

    public void setMostrarPrecioDolares(String mostrarPrecioDolares) {
        this.mostrarPrecioDolares = mostrarPrecioDolares;
    }

    public String getMostrarPrecioPesos() {
        return mostrarPrecioPesos;
    }

    public void setMostrarPrecioPesos(String mostrarPrecioPesos) {
        this.mostrarPrecioPesos = mostrarPrecioPesos;
    }
}
