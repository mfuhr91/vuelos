package com.vuelos.modelo;

import javax.swing.*;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Estadia {
    private Date fechaArribo;
    private Date horaArribo;
    private Date fechaSalida;
    private Date horaSalida;
    private double total;

    public Estadia(){}

    public Estadia(Date fechaArribo, Date fechaSalida,Date horaArribo, Date horaSalida){
        this.fechaArribo = fechaArribo;
        this.fechaSalida = fechaSalida;
        this.horaArribo = horaArribo;
        this.horaSalida = horaSalida;

    }

    public String mostrarFechaHoy(){
        Date hoy = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(hoy);
    }

    public String mostrarHoraActual(){
        Date horaActual = new Date();
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
        return simpleTimeFormat.format(horaActual);
    }

    public int totalHoras() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date hoy = new Date();
        String parseHoy = simpleDateFormat.format(hoy);
        String parseFechaInicial = simpleDateFormat.format(fechaArribo);
        Date fechaHoy = simpleDateFormat.parse(parseHoy);
        Date fechaInicial = simpleDateFormat.parse(parseFechaInicial);

        if(fechaArribo.equals(fechaHoy) || fechaArribo.after(fechaHoy)){
            if (fechaSalida.getTime() > fechaArribo.getTime()) {
                double totalArribo = (horaArribo.getTime());
                double dias = ((fechaSalida.getTime() - fechaArribo.getTime()));
                double totalSalida = (horaSalida.getTime() + dias);
                this.total = (totalSalida - totalArribo)/1000/60/60;

            } else if(fechaSalida.getTime() == fechaArribo.getTime()) {
                long totalArribo = (horaArribo.getTime()); // 11horas = 39600000milsec
                long totalSalida = (horaSalida.getTime()); //14hs = 50400000 milsec
                this.total = (totalSalida - totalArribo) / 1000 / 60 / 60;
            }
        }else{
                JOptionPane.showMessageDialog(null, "La fecha inicial no puede ser menor al día de hoy!");
        }

        return (int) Math.ceil(this.total);
    }
}