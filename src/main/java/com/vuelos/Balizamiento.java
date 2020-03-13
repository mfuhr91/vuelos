package com.vuelos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Balizamiento {


    public Container getPanelMain(){
        return panelMain;
    }
    private JPanel panelMain;
    private JScrollPane barraDesplazamiento;
    private JButton actualizarBtn;
    private JButton salirBtn;
}
