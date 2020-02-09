package com.vuelos.modelo;

public class TasaAterrizaje {

    private Vuelo vuelo;
    private String procedencia;
    private double menos5tn; //tasa mínima
    private double de5a30tn;
    private double de31a80tn;
    private double de81a170tn;
    private double mas170tn;


    public TasaAterrizaje(Vuelo vuelo){

        if(vuelo.getProcedencia().equals("cabotaje")){

            setMenos5tn(14.10);
            setDe5a30tn(1.05);
            setDe31a80tn(1.14);
            setDe81a170tn(1.26);
            setMas170tn(1.47);

            if(vuelo.getPeso() < 5.00){
                vuelo.setCostoAterrizaje(menos5tn);
            }else if(vuelo.getPeso() >= 5.00 && vuelo.getPeso() < 30.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * de5a30tn);
            }else if(vuelo.getPeso()  >= 31.00 && vuelo.getPeso() < 80.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * de31a80tn);
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * de81a170tn);
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * mas170tn);
            }
        }else{
            setMenos5tn(20.00);
            setDe5a30tn(4.62);
            setDe31a80tn(5.28);
            setDe81a170tn(6.49);
            setMas170tn(7.19);

            if(vuelo.getPeso() < 5.00){
                vuelo.setCostoAterrizaje(menos5tn);
            }else if(vuelo.getPeso() >= 5.00 && vuelo.getPeso() < 30.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * de5a30tn);
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

    public double getMenos5tn() {
        return menos5tn;
    }

    public void setMenos5tn(double menos5tn) {
        this.menos5tn = menos5tn;
    }

    public double getDe5a30tn() {
        return de5a30tn;
    }

    public void setDe5a30tn(double de5a30tn) {
        this.de5a30tn = de5a30tn;
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
