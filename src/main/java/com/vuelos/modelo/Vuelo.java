package com.vuelos.modelo;

public class Vuelo {

    private int id;
    private String nroVuelo;
    private String matricula;
    private int nroPax;
    private int peso;
    private String procedencia;
    private String destino;
    private double costoAterrizaje;
    private double costoEstacionamiento;
    private Estadia estadia;

    public Vuelo(String nroVuelo, String matricula, int peso, String procedencia, String destino, Estadia estadia) {
        this.nroVuelo = nroVuelo;
        this.matricula = matricula;
        this.peso = peso;
        this.procedencia = procedencia;
        this.destino = destino;
        this.estadia = estadia;
    }

    public Vuelo(String nroVuelo, String matricula, int nroPax, int peso, String procedencia, String destino, Estadia estadia) {
        this.nroVuelo = nroVuelo;
        this.matricula = matricula;
        this.nroPax = nroPax;
        this.peso = peso;
        this.procedencia = procedencia;
        this.destino = destino;
        this.estadia = estadia;
    }

    public int getNroPax() {
        return nroPax;
    }

    public int getPeso() {
        return peso;
    }

    public double getCostoAterrizaje() {
        return costoAterrizaje;
    }

    public void setCostoAterrizaje(double costoAterrizaje) {
        this.costoAterrizaje = costoAterrizaje;
    }

    public double getCostoEstacionamiento() {
        return costoEstacionamiento;
    }

    public void setCostoEstacionamiento(double costoEstacionamiento) {
        this.costoEstacionamiento = costoEstacionamiento;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public String getDestino() {
        return destino;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }
}
