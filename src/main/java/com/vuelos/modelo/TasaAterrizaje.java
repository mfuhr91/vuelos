package com.vuelos.modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.text.DecimalFormat;
import java.util.Date;


@XmlRootElement //(name="Tasas de Aterrizaje")
@XmlType(propOrder = {"tasaMinimaCab","menosDe30tnCab","de31a80tnCab","de81a170tnCab","mas170tnCab","tasaMinimaInter","menosDe30tnInter","de31a80tnInter",
"de81a170tnInter","mas170tnInter","fueraHorario"})
public class TasaAterrizaje {

    private Vuelo vuelo;
    private String procedencia;
    private double tasaMinimaCab;
    private double menosDe30tnCab;
    private double de31a80tnCab;
    private double de81a170tnCab;
    private double mas170tnCab;
    private double tasaMinimaInter;
    private double menosDe30tnInter;
    private double de31a80tnInter;
    private double de81a170tnInter;
    private double mas170tnInter;
    private double fueraHorario;
    private double subTotal;
    private double tasa;

    public TasaAterrizaje(){
        /*
        setTasaMinimaCab(14.10);
        setMenosDe30tnCab(1.05);
        setDe31a80tnCab(1.14);
        setDe81a170tnCab(1.26);
        setMas170tnCab(1.47);

        setTasaMinimaInter(20.00);
        setMenosDe30tnInter(4.62);
        setDe31a80tnInter(5.28);
        setDe81a170tnInter(6.49);
        setMas170tnInter(7.19);


         */

    }

    public TasaAterrizaje(Vuelo vuelo){
        if(vuelo.getProcedencia().equals("cabotaje")){

            if ((vuelo.getEstadia().getHoraArribo().getTime() <= 36000000)              // 10 horas = 7:00 hora en Argentina
                    || (vuelo.getEstadia().getHoraSalida().getTime() >= 93600000)) {    // 26hs = 23:00 hora en Argentina
                fueraHorario = 260.00;
            }

            setTasaMinimaCab(14.10);
            setMenosDe30tnCab(1.05);
            setDe31a80tnCab(1.14);
            setDe81a170tnCab(1.26);
            setMas170tnCab(1.47);


            if(vuelo.getPeso() < 30.00){
                subTotal = vuelo.getPeso() * menosDe30tnCab;
                if(subTotal <= tasaMinimaCab){
                    vuelo.setCostoAterrizaje(tasaMinimaCab+fueraHorario);
                    tasa = tasaMinimaCab;
                }else{
                    vuelo.setCostoAterrizaje(vuelo.getPeso() * (menosDe30tnCab+fueraHorario));
                    tasa = menosDe30tnCab;
                }
            }else if(vuelo.getPeso()  >= 31.00 && vuelo.getPeso() < 80.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * (de31a80tnCab+fueraHorario));
                tasa = de31a80tnCab;
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * (de81a170tnCab+fueraHorario));
                tasa = de81a170tnCab;
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * (mas170tnCab+fueraHorario));
                tasa = mas170tnCab;
            }

        }else{

            if ((vuelo.getEstadia().getHoraArribo().getTime() <= 36000000)              // 10 horas = 7:00 hora en Argentina
                    || (vuelo.getEstadia().getHoraSalida().getTime() >= 93600000)) {    // 26hs = 23:00 hora en Argentina
                fueraHorario = 260.00;
            }

            setTasaMinimaInter(20.00);
            setMenosDe30tnInter(4.62);
            setDe31a80tnInter(5.28);
            setDe81a170tnInter(6.49);
            setMas170tnInter(7.19);

            if(vuelo.getPeso() < 30.00){
                subTotal = vuelo.getPeso() * menosDe30tnInter;
                if(subTotal <= tasaMinimaInter){
                    vuelo.setCostoAterrizaje(tasaMinimaInter+fueraHorario);
                    tasa = tasaMinimaInter;
                }else{
                    vuelo.setCostoAterrizaje(vuelo.getPeso() * (menosDe30tnInter+fueraHorario));
                    tasa = menosDe30tnInter;
                }
            }else if(vuelo.getPeso() >= 31.00 && vuelo.getPeso() < 80.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * de31a80tnInter);
                tasa = de31a80tnInter;
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * de81a170tnInter);
                tasa = de81a170tnInter;
            }else if(vuelo.getPeso() >= 170.00){
                vuelo.setCostoAterrizaje(vuelo.getPeso() * mas170tnInter);
                tasa = mas170tnInter;
            }
        }
    }
    @XmlTransient
    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    @XmlTransient
    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }
    @XmlElement
    public double getTasaMinimaCab() {
        return tasaMinimaCab;
    }
    public void setTasaMinimaCab(double tasaMinimaCab) {
        this.tasaMinimaCab = tasaMinimaCab;
    }
    @XmlElement
    public double getMenosDe30tnCab() {
        return menosDe30tnCab;
    }

    public void setMenosDe30tnCab(double menosDe30tnCab) {
        this.menosDe30tnCab = menosDe30tnCab;
    }
    @XmlElement
    public double getDe31a80tnCab() {
        return de31a80tnCab;
    }

    public void setDe31a80tnCab(double de31a80tnCab) {
        this.de31a80tnCab = de31a80tnCab;
    }
    @XmlElement
    public double getDe81a170tnCab() {
        return de81a170tnCab;
    }

    public void setDe81a170tnCab(double de81a170tnCab) {
        this.de81a170tnCab = de81a170tnCab;
    }
    @XmlElement
    public double getMas170tnCab() {
        return mas170tnCab;
    }

    public void setMas170tnCab(double mas170tnCab) {
        this.mas170tnCab = mas170tnCab;
    }
    @XmlElement
    public double getTasaMinimaInter() {
        return tasaMinimaInter;
    }

    public void setTasaMinimaInter(double tasaMinimaInter) {
        this.tasaMinimaInter = tasaMinimaInter;
    }
    @XmlElement
    public double getMenosDe30tnInter() {
        return menosDe30tnInter;
    }

    public void setMenosDe30tnInter(double menosDe30tnInter) {
        this.menosDe30tnInter = menosDe30tnInter;
    }
    @XmlElement
    public double getDe31a80tnInter() {
        return de31a80tnInter;
    }

    public void setDe31a80tnInter(double de31a80tnInter) {
        this.de31a80tnInter = de31a80tnInter;
    }
    @XmlElement
    public double getDe81a170tnInter() {
        return de81a170tnInter;
    }

    public void setDe81a170tnInter(double de81a170tnInter) {
        this.de81a170tnInter = de81a170tnInter;
    }
    @XmlElement
    public double getMas170tnInter() {
        return mas170tnInter;
    }

    public void setMas170tnInter(double mas170tnInter) {
        this.mas170tnInter = mas170tnInter;
    }
    @XmlElement
    public double getFueraHorario() {
        return fueraHorario;
    }

    public void setFueraHorario(double fueraHorario) {
        this.fueraHorario = fueraHorario;
    }
    @XmlTransient
    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }
}
