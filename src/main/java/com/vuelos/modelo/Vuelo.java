package com.vuelos.modelo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"paxCab","paxInter"})
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
    private double paxCab;
    private double paxInter;

    public Vuelo(){};

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

    @XmlTransient
    public int getNroPax() {
        return nroPax;
    }

    @XmlTransient
    public int getPeso() {
        return peso;
    }

    @XmlTransient
    public double getCostoAterrizaje() {
        return costoAterrizaje;
    }

    public void setCostoAterrizaje(double costoAterrizaje) {
        this.costoAterrizaje = costoAterrizaje;
    }

    @XmlTransient
    public double getCostoEstacionamiento() {
        return costoEstacionamiento;
    }

    public void setCostoEstacionamiento(double costoEstacionamiento) {
        this.costoEstacionamiento = costoEstacionamiento;
    }

    @XmlTransient
    public String getProcedencia() {
        return procedencia;
    }

    @XmlTransient
    public String getDestino() {
        return destino;
    }
    @XmlTransient
    public Estadia getEstadia() {
        return estadia;
    }

    public double getPaxCab() {
        return paxCab;
    }

    public void setPaxCab(double paxCab) {
        this.paxCab = paxCab;
    }

    public double getPaxInter() {
        return paxInter;
    }

    public void setPaxInter(double paxInter) {
        this.paxInter = paxInter;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }
}
