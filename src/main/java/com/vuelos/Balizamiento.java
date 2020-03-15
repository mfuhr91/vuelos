package com.vuelos;

import com.sun.tools.corba.se.idl.StringGen;
import com.vuelos.modelo.SalidasPuestasDelSol;
import org.jdom2.JDOMFactory;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Balizamiento implements TableModel {


    public Container getPanelMain(){
        return panelMain;
    }
    private JTable tabla;
    private JPanel panelMain;
    private JButton actualizarBtn;
    private JButton salirBtn;
    private JScrollPane scrollPane;


    public Balizamiento(){
        /*
        SalidasPuestasDelSol salidasPuestasDelSol = new SalidasPuestasDelSol();
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<SalidasPuestasDelSol> fila = new ArrayList<>();

        Date horaSalida = new Date();
        horaSalida = salidasPuestasDelSol.getSalidaSol();

        fila.add(new SalidasPuestasDelSol(horaSalida,



        String [] columnas = {"Salida del Sol", "Puesta del Sol","Fecha desde","Fecha hasta"};

        /*
        Object [][] datos = {{"maria","40","Española","3242435"},

                {"mariano","28","argentino","434236745"},
                {"maria","32","Española","3242435"},
                {"maria","40","Española","3242435"}};



        tabla = new JTable(datos, columnas);
        tabla.setPreferredScrollableViewportSize(new Dimension(500,50));
        tabla.setFillsViewportHeight(true);
        tabla.set


         */



    }




    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
