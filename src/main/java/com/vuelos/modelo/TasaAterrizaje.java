package com.vuelos.modelo;

import java.util.Date;

public class TasaAterrizaje {

    private Vuelo vuelo;
    private String procedencia;
    private double tasaMinima;
    private double menosDe30tn;
    private double de31a80tn;
    private double de81a170tn;
    private double mas170tn;
    private double fueraHorario;
    private double subTotal;


    public TasaAterrizaje(Vuelo vuelo){

        if(vuelo.getProcedencia().equals("cabotaje")){

            if(((vuelo.getEstadia().getFechaSalida().getTime() - vuelo.getEstadia().getFechaArribo().getTime()) +
                    vuelo.getEstadia().getHoraSalida().getTime()) >= 93600000 ||
                    (vuelo.getEstadia().getHoraArribo().getTime() <= 36000000) ||
                    (vuelo.getEstadia().getHoraSalida().getTime() >= 93600000)) {
                fueraHorario = 260.00;
            }

            setTasaMinima(14.10);
            setMenosDe30tn(1.05);
            setDe31a80tn(1.14);
            setDe81a170tn(1.26);
            setMas170tn(1.47);


            if(vuelo.getPeso() < 30.00){
                subTotal = vuelo.getPeso() * menosDe30tn;
                if(subTotal <= tasaMinima){
                    vuelo.setCostoAterrizaje(tasaMinima+fueraHorario);
                }else{
                    vuelo.setCostoAterrizaje(vuelo.getPeso() * (menosDe30tn+fueraHorario));
                }
            }else if(vuelo.getPeso()  >= 31.00 && vuelo.getPeso() < 80.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * (de31a80tn+fueraHorario));
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * (de81a170tn+fueraHorario));
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * (mas170tn+fueraHorario));
            }
        }else{

            if(((vuelo.getEstadia().getFechaSalida().getTime() - vuelo.getEstadia().getFechaArribo().getTime()) +
                    vuelo.getEstadia().getHoraSalida().getTime()) >= 93600000 ||
                    (vuelo.getEstadia().getHoraArribo().getTime() <= 36000000) ||
                    (vuelo.getEstadia().getHoraSalida().getTime() >= 93600000)) {
                fueraHorario = 260.00;
            }

            setTasaMinima(20.00);
            setMenosDe30tn(4.62);
            setDe31a80tn(5.28);
            setDe81a170tn(6.49);
            setMas170tn(7.19);

            if(vuelo.getPeso() < 30.00){
                subTotal = vuelo.getPeso() * menosDe30tn;
                if(subTotal <= tasaMinima){
                    vuelo.setCostoAterrizaje(tasaMinima+fueraHorario);
                }else{
                    vuelo.setCostoAterrizaje(vuelo.getPeso() * (menosDe30tn+fueraHorario));
                }
            }else if(vuelo.getPeso() >= 31.00 && vuelo.getPeso() < 80.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * de31a80tn);
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * de81a170tn);
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * mas170tn);
            }
        }

    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public double getTasaMinima() {
        return tasaMinima;
    }

    public void setTasaMinima(double tasaMinima) {
        this.tasaMinima = tasaMinima;
    }

    public double getMenosDe30tn() {
        return menosDe30tn;
    }

    public void setMenosDe30tn(double menosDe30tn) {
        this.menosDe30tn = menosDe30tn;
    }

    public double getDe31a80tn() {
        return de31a80tn;
    }

    public void setDe31a80tn(double de31a80tn) {
        this.de31a80tn = de31a80tn;
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
