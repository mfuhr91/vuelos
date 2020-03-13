package com.vuelos.modelo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;

@XmlRootElement //(name="Tarifario de Estacionamiento")
@XmlType(propOrder = {"tasaMinimaCab","menos80tnCab","de81a170tnCab","mas170tnCab","tasaMinimaInter","menos80tnInter",
        "de81a170tnInter","mas170tnInter",})
public class TasaEstacionamiento {

    private Vuelo vuelo;
    private double tasaMinimaCab; //tasa mínima
    private double menos80tnCab;
    private double de81a170tnCab;
    private double mas170tnCab;

    private double tasaMinimaInter; //tasa mínima
    private double menos80tnInter;
    private double de81a170tnInter;
    private double mas170tnInter;
    private double tasa;

    public TasaEstacionamiento(){

    }
    public TasaEstacionamiento(Vuelo vuelo) throws ParseException {
        asignarCosto(vuelo);
    }

    public double asignarCosto(Vuelo vuelo){
        TasaEstacionamiento estacionamiento = Persistencia.cargarEstacionamiento();
        if(vuelo.getProcedencia().equals("cabotaje")){
            setTasaMinimaCab(estacionamiento.getTasaMinimaCab());
            setMenos80tnCab(estacionamiento.getMenos80tnCab());
            setDe81a170tnCab(estacionamiento.getDe81a170tnCab());
            setMas170tnCab(estacionamiento.getMas170tnCab());

            if(vuelo.getPeso() < 80.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * menos80tnCab);
                tasa = menos80tnCab;
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de81a170tnCab);
                tasa = de81a170tnCab;
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * mas170tnCab);
                tasa = mas170tnCab;
            }

        }else{
            setTasaMinimaInter(estacionamiento.getTasaMinimaInter());
            setMenos80tnInter(estacionamiento.getMenos80tnInter());
            setDe81a170tnInter(estacionamiento.getDe81a170tnInter());
            setMas170tnInter(estacionamiento.getMas170tnInter());

            if(vuelo.getPeso() < 80.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * menos80tnInter);
                tasa = menos80tnInter;
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de81a170tnInter);
                tasa = de81a170tnInter;
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * mas170tnInter);
                tasa = mas170tnInter;
            }

        }
        return tasa;
    }

    public double getDe81a170tnCab() {
        return de81a170tnCab;
    }

    public void setDe81a170tnCab(double de81a170tnCab) {
        this.de81a170tnCab = de81a170tnCab;
    }

    public double getMas170tnCab() {
        return mas170tnCab;
    }

    public void setMas170tnCab(double mas170tnCab) {
        this.mas170tnCab = mas170tnCab;
    }

    public double getDe81a170tnInter() {
        return de81a170tnInter;
    }

    public void setDe81a170tnInter(double de81a170tnInter) {
        this.de81a170tnInter = de81a170tnInter;
    }

    public double getMas170tnInter() {
        return mas170tnInter;
    }

    public void setMas170tnInter(double mas170tnInter) {
        this.mas170tnInter = mas170tnInter;
    }

    public double getTasaMinimaCab() {
        return tasaMinimaCab;
    }

    public void setTasaMinimaCab(double tasaMinimaCab) {
        this.tasaMinimaCab = tasaMinimaCab;
    }

    public double getTasaMinimaInter() {
        return tasaMinimaInter;
    }

    public void setTasaMinimaInter(double tasaMinimaInter) {
        this.tasaMinimaInter = tasaMinimaInter;
    }

    public double getMenos80tnCab() {
        return menos80tnCab;
    }

    public void setMenos80tnCab(double menos80tnCab) {
        this.menos80tnCab = menos80tnCab;
    }

    public double getMenos80tnInter() {
        return menos80tnInter;
    }

    public void setMenos80tnInter(double menos80tnInter) {
        this.menos80tnInter = menos80tnInter;
    }

    @XmlTransient
    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }
}
