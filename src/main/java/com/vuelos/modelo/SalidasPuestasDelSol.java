package com.vuelos.modelo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlRootElement
@XmlType(propOrder = {"salidaSol", "puestaSol", "fechaDesde", "fechaHasta"})
public class SalidasPuestasDelSol {

    private Date salidaSol;
    private Date puestaSol;
    private Date fechaDesde;
    private Date fechaHasta;

    public SalidasPuestasDelSol(){}

    public SalidasPuestasDelSol(Date salidaSol, Date puestaSol, Date fechaDesde, Date fechaHasta) {
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
}
