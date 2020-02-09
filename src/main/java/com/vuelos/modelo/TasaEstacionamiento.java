package com.vuelos.modelo;

import java.text.ParseException;

public class TasaEstacionamiento {

    private Vuelo vuelo;
    private double menos5tn; //tasa mínima
    private double de5a80tn;
    private double de81a170tn;
    private double mas170tn;

    public TasaEstacionamiento(Vuelo vuelo) throws ParseException {

        if(vuelo.getProcedencia().equals("cabotaje")){

            setMenos5tn(5.20);
            setDe5a80tn(0.10);
            setDe81a170tn(0.13);
            setMas170tn(0.17);

            if(vuelo.getPeso() < 5.00){
                vuelo.setCostoEstacionamiento(menos5tn);
            }else if(vuelo.getPeso() >= 5.00 && vuelo.getPeso() < 80.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de5a80tn);
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de81a170tn);
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * mas170tn);
            }
        }else{
            setMenos5tn(4.00);
            setDe5a80tn(0.14);
            setDe81a170tn(0.16);
            setMas170tn(0.18);

            if(vuelo.getPeso() < 5.00){
                vuelo.setCostoEstacionamiento(menos5tn);
            }else if(vuelo.getPeso() >= 5.00 && vuelo.getPeso() < 80.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de5a80tn);
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * de81a170tn);
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoEstacionamiento(vuelo.getPeso() * mas170tn);
            }
        }
    }

    public void setMenos5tn(double menos5tn) {
        this.menos5tn = menos5tn;
    }

    public double getDe5a80tn() {
        return de5a80tn;
    }

    public void setDe5a80tn(double de5a80tn) {
        this.de5a80tn = de5a80tn;
    }

    public double getDe81a170tn() {
        return de81a170tn;
    }

    public void setDe81a170tn(double de81a170tn) {
        this.de81a170tn = de81a170tn;
    }

    public double getMas170tn() {
        return mas170tn;
    }

    public void setMas170tn(double mas170tn) {
        this.mas170tn = mas170tn;
    }
}
