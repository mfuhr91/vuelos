package com.vuelos.modelo;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Movimiento {

    private int nroMovimiento;
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
    private double costoAterrizaje;
    private double costoEstacionamiento;
    private double tasa;
    private double subTotalCostoAterrizaje;
    private double subTotalCostoEstacionamiento;
    private double subTotalCostoBalizamiento;



    public Movimiento(Vuelo vuelo, Estadia estadia, double cambioMoneda) {
        this.vuelo = vuelo;
        this.estadia = estadia;
        this.cambioMoneda = cambioMoneda;
    }

    // Calcula costos de movimiento
    public void calcularCostos() throws ParseException {

        DecimalFormat df = new DecimalFormat("0.00");
        if (vuelo.getProcedencia().equals("cabotaje") && vuelo.getDestino().equals("cabotaje")){ // PAX y MOV en pesos
            costoPax = vuelo.getNroPax() * vuelo.getPaxCab();
            costoPistaCab();
            /*
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

             */
                //setTasa(estacionamiento.getTasa());
                enPesos = precio + costoPax;
                enDolares = enPesos / cambioMoneda;
                mostrarPrecio = ("AR$" + df.format(precio));
                mostrarCostoPax = ("AR$" + df.format(costoPax));
                mostrarPrecioPesos = ("AR$" + df.format(enPesos));
                mostrarPrecioDolares = ("USD" + df.format(enDolares));

        } else if (vuelo.getProcedencia().equals("internacional") && vuelo.getDestino().equals("cabotaje")) { //PAX pesos - MOV dolares
            costoPax = vuelo.getNroPax() * vuelo.getPaxCab();
            costoPistaInter();

                enDolares = precio + (costoPax / cambioMoneda);
                enPesos = (precio * cambioMoneda) + costoPax;
                mostrarPrecio = ("USD" + df.format(precio));
                mostrarCostoPax = ("AR$" + df.format(costoPax));
                mostrarPrecioPesos = ("AR$" + df.format(enPesos));
                mostrarPrecioDolares = ("USD" + df.format(enDolares));

        } else if (vuelo.getProcedencia().equals("cabotaje") && vuelo.getDestino().equals("internacional")) { // PAX dolares - MOV pesos
            costoPax = vuelo.getNroPax()  * vuelo.getPaxInter();

            costoPistaCab();

            enDolares =  (precio/cambioMoneda) + costoPax;
            enPesos = precio + (costoPax*cambioMoneda);
            mostrarPrecio = ("AR$" + df.format(precio));
            mostrarCostoPax = ("USD" + df.format(costoPax));
            mostrarPrecioPesos = ("AR$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));

        } else if (vuelo.getProcedencia().equals("internacional") && vuelo.getDestino().equals("internacional")) { //PAX dolares - MOV dolares
            costoPax = vuelo.getNroPax() * vuelo.getPaxInter();

            costoPistaInter();

            enDolares =  precio + costoPax;
            enPesos = enDolares*cambioMoneda;
            mostrarPrecio = ("USD" + df.format(precio));
            mostrarCostoPax = ("USD" + df.format(costoPax));
            mostrarPrecioPesos = ("AR$" + df.format(enPesos));
            mostrarPrecioDolares = ("USD" + df.format(enDolares));
        }
    }

    public void costoPistaCab() throws ParseException {
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

    public void costoPistaInter() throws ParseException {
        TasaEstacionamiento estacionamiento = Persistencia.cargarEstacionamiento();
        if((vuelo.getCostoEstacionamiento() * estadia.totalHoras()) <= estacionamiento.getTasaMinimaInter()){
            precio = vuelo.getCostoAterrizaje() + (estacionamiento.getTasaMinimaInter());
            setTasa(estacionamiento.getTasaMinimaInter());
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

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public double getSubTotalCostoAterrizaje() {
        return subTotalCostoAterrizaje;
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

    public double getSubTotalCostoBalizamiento() {
        return subTotalCostoBalizamiento;
    }

    public void setSubTotalCostoBalizamiento(double subTotalCostoBalizamiento) {
        this.subTotalCostoBalizamiento = subTotalCostoBalizamiento;
    }
}
