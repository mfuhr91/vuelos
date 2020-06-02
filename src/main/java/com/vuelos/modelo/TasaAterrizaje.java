package com.vuelos.modelo;

import com.vuelos.vistas.Balizamiento;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que pertenece al calculo del costo de aterrizaje de la aeronave
 */
@XmlRootElement
@XmlType(propOrder = {"tasaMinimaCab","menosDe30tnCab","de31a80tnCab","de81a170tnCab","mas170tnCab",
        "tasaMinimaInter","menosDe30tnInter","de31a80tnInter","de81a170tnInter","mas170tnInter",
        "fueraHorario","horaApertura","horaCierre","tasaBalizamiento","inviernoDesde","inviernoHasta",
        "tasaInviernoCab","tasaInviernoInter"})
public class TasaAterrizaje {

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
    private Date horaApertura;
    private Date horaCierre;
    private double tasaBalizamiento;
    private Date inviernoDesde;
    private Date inviernoHasta;
    private double tasaInviernoCab;
    private double tasaInviernoInter;
    private double labelTasaInvernal;
    private double subTotal;
    private double total;
    private double tasa;
    private Date salidaSol;
    private Date puestaSol;
    private Date fechaDesde;
    private Date fechaHasta;


    public TasaAterrizaje(){
    }


    public TasaAterrizaje(Vuelo vuelo) throws JAXBException {
        System.out.println("Se cargan valores en nuevo Aterrizaje");
        TasaAterrizaje aterrizaje = Persistencia.cargarAterrizaje();
        ArrayList<SalidasPuestasDelSol> fila = Persistencia.cargarBalizamiento().getSalidasPuestasDelSol();

        for (SalidasPuestasDelSol sps : fila) {
            salidaSol = sps.getSalidaSol();
            puestaSol = sps.getPuestaSol();
            fechaDesde = sps.getFechaDesde();
            fechaHasta = sps.getFechaHasta();
            // SE ASIGNA EL PORCENTAJE DE TASA DE BALIZAMIENTO EN CASO DE SER NECESARIO
            if(     vuelo.getEstadia().getFechaArribo().after(fechaDesde) &&
                    vuelo.getEstadia().getFechaArribo().before(fechaHasta)){
                if(     vuelo.getEstadia().getHoraArribo().before(salidaSol) ||
                        vuelo.getEstadia().getHoraArribo().after(puestaSol) ||
                        vuelo.getEstadia().getHoraArribo().equals(puestaSol)){
                tasaBalizamiento = Double.parseDouble(String.format("1.%d", Math.round(aterrizaje.getTasaBalizamiento())));
                System.out.println("CUMPLE CONDICIONES");
                break;
                }
            }else{
                tasaBalizamiento = 1.0;
            }
        }
        // SE ASIGNA EL COSTO FUERA DE HORARIO EN CASO DE SER NECESARIO
        if (vuelo.getEstadia().getHoraArribo().before(aterrizaje.getHoraApertura())
                || vuelo.getEstadia().getHoraSalida().after(aterrizaje.getHoraCierre())
                || vuelo.getEstadia().getHoraSalida().equals(aterrizaje.getHoraCierre())) {
            fueraHorario = aterrizaje.getFueraHorario();
        }

        // EN CASO DE SER CABOTAJE, SE ASIGNAN LOS VALOES EN PESOS
        if(vuelo.getProcedencia().equals("cabotaje")){
            // SE ASIGNA EL COSO DE TASA INVERNAL EN CASO DE SER NECESARIO
            if(vuelo.getEstadia().getFechaArribo().after(aterrizaje.getInviernoDesde()) ||
                    vuelo.getEstadia().getFechaArribo().equals(aterrizaje.getInviernoDesde()) ||
                    vuelo.getEstadia().getFechaArribo().before(aterrizaje.getInviernoHasta())){
                tasaInviernoCab = aterrizaje.getTasaInviernoCab();
                labelTasaInvernal = tasaInviernoCab;
            }

            setTasaMinimaCab(aterrizaje.getTasaMinimaCab());
            setMenosDe30tnCab(aterrizaje.getMenosDe30tnCab());
            setDe31a80tnCab(aterrizaje.getDe31a80tnCab());
            setDe81a170tnCab(aterrizaje.getDe81a170tnCab());
            setMas170tnCab(aterrizaje.getMas170tnCab());

            if(vuelo.getPeso() < 30.00){
                subTotal = vuelo.getPeso() * menosDe30tnCab;
                if(subTotal <= tasaMinimaCab){
                    subTotal = tasaMinimaCab;
                    total = (tasaMinimaCab * tasaBalizamiento) + fueraHorario + tasaInviernoCab;
                    vuelo.setCostoAterrizaje(total);
                    tasa = tasaMinimaCab;
                }else{
                    subTotal = vuelo.getPeso() * menosDe30tnCab;
                    total = (subTotal * tasaBalizamiento) + fueraHorario + tasaInviernoCab;
                    vuelo.setCostoAterrizaje(total);
                    tasa = menosDe30tnCab;
                }
            }else if(vuelo.getPeso()  >= 31.00 && vuelo.getPeso() < 80.00){
                subTotal = vuelo.getPeso() * de31a80tnCab;
                total = (subTotal * tasaBalizamiento) + fueraHorario + tasaInviernoCab;
                vuelo.setCostoAterrizaje(total);
                tasa = de31a80tnCab;
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                subTotal = vuelo.getPeso() * de81a170tnCab;
                total = (subTotal * tasaBalizamiento) + fueraHorario + tasaInviernoCab;
                vuelo.setCostoAterrizaje(total);
                tasa = de81a170tnCab;
            }else if(vuelo.getPeso() >= 170.00){
                subTotal = vuelo.getPeso() * mas170tnCab;
                total = (subTotal * tasaBalizamiento) + fueraHorario + tasaInviernoCab;
                vuelo.setCostoAterrizaje(total);
                tasa = mas170tnCab;
            }

            // EN CASO DE SER INTERNACIONAL, SE ASIGNAN LOS VALORES EN DOLARES
        }else{
            if(vuelo.getEstadia().getFechaArribo().after(aterrizaje.getInviernoDesde()) ||
                    vuelo.getEstadia().getFechaArribo().equals(aterrizaje.getInviernoDesde()) ||
                    vuelo.getEstadia().getFechaArribo().before(aterrizaje.getInviernoHasta())){
                tasaInviernoInter = aterrizaje.getTasaInviernoInter();
                labelTasaInvernal = tasaInviernoInter;
            }

            setTasaMinimaInter(aterrizaje.getTasaMinimaInter());
            setMenosDe30tnInter(aterrizaje.getMenosDe30tnInter());
            setDe31a80tnInter(aterrizaje.getDe31a80tnInter());
            setDe81a170tnInter(aterrizaje.getDe81a170tnInter());
            setMas170tnInter(aterrizaje.getMas170tnInter());

            if(vuelo.getPeso() < 30.00){
                subTotal = vuelo.getPeso() * menosDe30tnInter;
                if(subTotal <= tasaMinimaInter){
                    subTotal = tasaMinimaInter;
                    total = (subTotal * tasaBalizamiento) + fueraHorario + tasaInviernoInter;
                    vuelo.setCostoAterrizaje(total);
                    tasa = tasaMinimaInter;
                }else{
                    subTotal = vuelo.getPeso() * menosDe30tnInter;
                    total = (subTotal *tasaBalizamiento) + fueraHorario + tasaInviernoInter;
                    vuelo.setCostoAterrizaje(total);
                    tasa = menosDe30tnInter;
                }
            }else if(vuelo.getPeso() >= 31.00 && vuelo.getPeso() < 80.00){
                subTotal = vuelo.getPeso() * de31a80tnInter;
                total = (subTotal * tasaBalizamiento) + fueraHorario + tasaInviernoInter;
                vuelo.setCostoAterrizaje(total);
                tasa = de31a80tnInter;
            }else if(vuelo.getPeso() >= 81.00 && vuelo.getPeso() < 170.00){
                subTotal = vuelo.getPeso() * de81a170tnInter;
                total = (subTotal * tasaBalizamiento) + fueraHorario + tasaInviernoInter;
                vuelo.setCostoAterrizaje(total);
                tasa = de81a170tnInter;
            }else if(vuelo.getPeso() >= 170.00){
                subTotal = vuelo.getPeso() * mas170tnInter;
                total = (subTotal * tasaBalizamiento) + fueraHorario + tasaInviernoInter;
                vuelo.setCostoAterrizaje(total);
                tasa = mas170tnInter;
            }
        }
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

    public double getTasaBalizamiento() {
        return tasaBalizamiento;
    }

    public void setTasaBalizamiento(double tasaBalizamiento) {
        this.tasaBalizamiento = tasaBalizamiento;
    }

    @XmlTransient
    public double getSubTotal() {
        return subTotal;
    }

    public Date getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(Date horaApertura) {
        this.horaApertura = horaApertura;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Date getInviernoDesde() {
        return inviernoDesde;
    }

    public void setInviernoDesde(Date inviernoDesde) {
        this.inviernoDesde = inviernoDesde;
    }

    public Date getInviernoHasta() {
        return inviernoHasta;
    }

    public void setInviernoHasta(Date inviernoHasta) {
        this.inviernoHasta = inviernoHasta;
    }

    public double getTasaInviernoCab() {
        return tasaInviernoCab;
    }

    public void setTasaInviernoCab(double tasaInviernoCab) {
        this.tasaInviernoCab = tasaInviernoCab;
    }

    public double getTasaInviernoInter() {
        return tasaInviernoInter;
    }

    public void setTasaInviernoInter(double tasaInviernoInter) {
        this.tasaInviernoInter = tasaInviernoInter;
    }
    @XmlTransient
    public double getLabelTasaInvernal() {
        return labelTasaInvernal;
    }
}
