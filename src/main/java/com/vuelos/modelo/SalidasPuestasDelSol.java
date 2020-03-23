package com.vuelos.modelo;

import javax.xml.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@XmlType(propOrder = {"id","salidaSol", "puestaSol", "fechaDesde", "fechaHasta"})
public class SalidasPuestasDelSol {

    private int id;
    private Date salidaSol;
    private Date puestaSol;
    private Date fechaDesde;
    private Date fechaHasta;

    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public SalidasPuestasDelSol(){}

    public SalidasPuestasDelSol(int id,Date salidaSol, Date puestaSol, Date fechaDesde, Date fechaHasta) {
        this.id = id;
        this.salidaSol = salidaSol;
        this.puestaSol = puestaSol;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public Date getSalidaSol() {

        return salidaSol;
    }

    public void setSalidaSol(Date salidaSol) {
        this.salidaSol = salidaSol;
    }

    public Date getPuestaSol() {
        return puestaSol;
    }

    public void setPuestaSol(Date puestaSol) {
        this.puestaSol = puestaSol;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
