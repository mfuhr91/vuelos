package com.vuelos.modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;

@XmlRootElement //(name="Tasas de Estacionamiento")
@XmlType(propOrder = {"menos5tnCab","de5a80tnCab","de81a170tnCab","mas170tnCab","menos5tnInter","de5a80tnInter",
        "de81a170tnInter","mas170tnInter",})
public class TasaEstacionamiento {

    private Vuelo vuelo;
    private double menos5tnCab; //tasa mínima
    private double de5a80tnCab;
    private double de81a170tnCab;
    private double mas170tnCab;

    private double menos5tnInter; //tasa mínima
    private double de5a80tnInter;
    private double de81a170tnInter;
    private double mas170tnInter;
    private double tasa;

    public TasaEstacionamiento(){
        setMenos5tnCab(5.20);
        setDe5a80tnCab(0.10);
        setDe81a170tnCab(0.13);
        setMas170tnCab(0.17);

        setMenos5tnInter(4.00);
        setDe5a80tnInter(0.14);
        setDe81a170tnInter(0.16);
        setMas170tnInter(0.18);
    }
    public TasaEstacionamiento(Vuelo vuelo) throws ParseException {
        this.vuelo = vuelo;
        if(vuelo.getProcedencia().equals("cabotaje")){

            setMenos5tnCab(5.20);
            setDe5a80tnCab(0.10);
            setDe81a170tnCab(0.13);
            setMas170tnCab(0.17);
            if(vuelo.getPeso() < 5.00){
                vuelo.setCostoEstacionamiento(menos5tnCab);
                tasa = menos5tnCab;
            }else if(vuelo.getPeso() >= 5.00 && vuelo.getPeso() < 80.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de5a80tnCab);
                tasa = de5a80tnCab;
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de81a170tnCab);
                tasa = de81a170tnCab;
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * mas170tnCab);
                tasa = mas170tnCab;
            }
        }else{
            setMenos5tnInter(4.00);
            setDe5a80tnInter(0.14);
            setDe81a170tnInter(0.16);
            setMas170tnInter(0.18);

            if(vuelo.getPeso() < 5.00){
                vuelo.setCostoEstacionamiento(menos5tnInter);
                tasa = menos5tnInter;
            }else if(vuelo.getPeso() >= 5.00 && vuelo.getPeso() < 80.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de5a80tnInter);
                tasa = de5a80tnInter;
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de81a170tnInter);
                tasa = de81a170tnInter;
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * mas170tnInter);
                tasa = mas170tnInter;
            }
        }
    }
    @XmlElement
    public double getMenos5tnCab() {
        return menos5tnCab;
    }

    public void setMenos5tnCab(double menos5tnCab) {
        this.menos5tnCab = menos5tnCab;
    }

    public double getDe5a80tnCab() {
        return de5a80tnCab;
    }

    public void setDe5a80tnCab(double de5a80tnCab) {
        this.de5a80tnCab = de5a80tnCab;
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

    public double getMenos5tnInter() {
        return menos5tnInter;
    }

    public void setMenos5tnInter(double menos5tnInter) {
        this.menos5tnInter = menos5tnInter;
    }

    public double getDe5a80tnInter() {
        return de5a80tnInter;
    }

    public void setDe5a80tnInter(double de5a80tnInter) {
        this.de5a80tnInter = de5a80tnInter;
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
    @XmlTransient
    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }
}
