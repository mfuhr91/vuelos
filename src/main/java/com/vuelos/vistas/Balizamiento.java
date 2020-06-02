package com.vuelos.vistas;

import com.vuelos.modelo.Persistencia;
import com.vuelos.modelo.SalidasPuestasDelSol;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase perteneciente a la ventana Salidas Y Puestas del Sol
 */
@XmlRootElement
@XmlType(propOrder = {"salidasPuestasDelSol"})
public class Balizamiento {

    Renderer cellRenderer = new Renderer();
    ColumnRenderer columnRenderer = new ColumnRenderer();
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private JPanel panelMain;
    private JTable tabla;
    private JLabel mensaje;
    private JButton actualizarBtn;
    private DefaultTableModel modeloTabla;
    private ArrayList<SalidasPuestasDelSol> salidasPuestasDelSol;
    private Object[] columnas = {"ID","Salida del Sol", "Puesta del Sol", "Fecha Desde", "Fecha Hasta"};

    public Balizamiento(ArrayList<SalidasPuestasDelSol> salidasPuestasDelSol) {
        this.salidasPuestasDelSol = salidasPuestasDelSol;
    }

    public Balizamiento() {}


    /**
     * metodo que crea toda la interfaz visual
     */
    public void mostrarVentana(){
        modeloTabla = new DefaultTableModel(null,columnas){
          public boolean isCellEditable(int fila, int columna){
            if(columna == 0){
                return false;
            }else{
                return true;
            }
          }

        };
        tabla = new JTable(modeloTabla);
        GridBagConstraints formato = new GridBagConstraints();
        Font fuente = new Font("Roboto Thin", 0, 16);

        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(0).setMaxWidth(30);
        columnModel.getColumn(1).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(70);

        tabla.getTableHeader().setDefaultRenderer(columnRenderer);
        tabla.getTableHeader().setFont(fuente);

        JFrame frame = new JFrame("Salidas y Puestas del Sol");
        Insets margenAgregarBoton = new Insets(5,5,5,5);
        Insets margenSeparador = new Insets(0,0,20,0);
        Insets margenBoton = new Insets(80,0,0,0);

        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setFont(fuente);
        tabla.setIntercellSpacing(new Dimension(0,1)); // tamaño del separadar de celdas
        tabla.setRowHeight(30); // alto de la celda

        panelMain = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(tabla);
        formato.gridx = 0; // columna n
        formato.gridy = 0; // fila n
        formato.gridwidth = 4; // ocupa n columnas
        formato.gridheight = 5; // ocupa n filas
        formato.fill = GridBagConstraints.BOTH;
        formato.anchor = GridBagConstraints.NORTHWEST;
        formato.weightx = 1; // peso de columna
        formato.weighty = 1; // peso de fila
        scrollPane.setFont(fuente);
        panelMain.add(scrollPane, formato);
        formato.weightx = 0; // peso de columna
        formato.weighty = 0; // peso de fila

        JButton agregarFilaBtn = new JButton("+ Fila");
        formato.fill = GridBagConstraints.NONE;
        formato.gridx = 0;
        formato.gridy = 8;
        formato.gridwidth = 1;
        formato.gridheight = 1;
        formato.insets = margenAgregarBoton;
        formato.anchor = GridBagConstraints.SOUTHEAST;
        agregarFilaBtn.setFont(fuente);
        agregarFilaBtn.setPreferredSize(new Dimension(75,35));
        agregarFilaBtn.setMaximumSize(new Dimension(75,35));
        panelMain.add(agregarFilaBtn,formato);

        JButton borrarFilaBtn = new JButton("- Fila");
        formato.fill = GridBagConstraints.NONE;
        formato.gridx = 3;
        formato.gridy = 8;
        formato.gridwidth = 0;
        formato.gridheight = 1;
        formato.insets = margenAgregarBoton;
        formato.anchor = GridBagConstraints.SOUTHEAST;
        borrarFilaBtn.setFont(fuente);
        borrarFilaBtn.setPreferredSize(new Dimension(75,35));
        borrarFilaBtn.setMaximumSize(new Dimension(75,35));
        panelMain.add(borrarFilaBtn,formato);

        JSeparator separator = new JSeparator();
        formato.fill = GridBagConstraints.BOTH;
        formato.gridx = 0;
        formato.gridy = 10;
        formato.insets = margenSeparador;
        panelMain.add(separator, formato);

        mensaje = new JLabel();
        formato.insets = margenSeparador;
        mensaje.setEnabled(false);
        panelMain.add(mensaje,formato);

        actualizarBtn = new JButton("Actualizar");
        formato.fill = GridBagConstraints.NONE;
        formato.gridx = 3;
        formato.gridy = 10;
        formato.gridwidth = 1;
        formato.gridheight = 1;
        formato.insets = margenBoton;
        formato.anchor = GridBagConstraints.SOUTHEAST;
        actualizarBtn.setFont(fuente);
        actualizarBtn.setEnabled(false);
        actualizarBtn.setPreferredSize(new Dimension(150,35));
        actualizarBtn.setMaximumSize(new Dimension(150,35));
        panelMain.add(actualizarBtn,formato);

        tabla.setDefaultRenderer(Object.class,cellRenderer);

        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.add(panelMain);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(600,770);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cargarDatos();

        agregarFilaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloTabla.setRowCount(modeloTabla.getRowCount()+1);
                modeloTabla.setValueAt(modeloTabla.getRowCount(),modeloTabla.getRowCount()-1,0);
                unSoloClickEnCeldas();
                actualizarBtn.setEnabled(false);
                try {
                    validarDatos();
                } catch (ParseException ex) {}
            }
        });

        borrarFilaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = modeloTabla.getRowCount()-1; i >= 0 ; i--) {
                    if (    modeloTabla.getValueAt(i, 1) != null &&
                            modeloTabla.getValueAt(i, 2) != null &&
                            modeloTabla.getValueAt(i, 3) != null &&
                            modeloTabla.getValueAt(i, 4) != null) {

                        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere borrar la fila " + (i + 1) + "?",
                                "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(getClass().getResource("")));
                        if (confirm == 0) { // Yes en Ventana Emergente
                            modeloTabla.setRowCount(modeloTabla.getRowCount() - 1);
                            break;
                        } else {
                            break;
                        }


                    } else {
                        modeloTabla.setRowCount(modeloTabla.getRowCount() - 1);
                        break;
                    }

                }
            }
        });


        actualizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere realizar los cambios?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                            new ImageIcon(getClass().getResource("")));
                    if (confirm == 0) { // Yes en Ventana Emergente
                        actualizarDatos();
                        JOptionPane.showMessageDialog(null,
                                "¡Se actualizaron los datos correctamente!","Confirmación",
                                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("")));
                    } else {

                    }
                } catch (ParseException ex) {}
            }
        });


        // SACA EL FOCO DE LA TABLA PARA GUARDAR LOS DATOS CORRECTAMENTE
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (tabla.getCellEditor() != null) {
                    tabla.getCellEditor().stopCellEditing();
                }
            }
        });

        scrollPane.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                if (tabla.getCellEditor() != null) {
                    tabla.getCellEditor().stopCellEditing();
                }
            }
        });
        tabla.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        // FIN CODIGO FOCO DE TABLA

        modeloTabla.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evento) {

                try {
                    actualizarBtn.setEnabled(validarDatos());
                } catch (ParseException e) {
                    actualizarBtn.setEnabled(false);
                    mensaje.setText("¡Ingrese el formato 'HH:MM' para la hora  y 'DD/MM/AAAA' para la fecha!");
                }
            }
        });

    }

    /**
     * metodo para validar datos antes de habiltar el boton Actualizar
     * @return verdadero para habilitar boton
     * @throws ParseException error de formato
     */
    public boolean validarDatos() throws ParseException {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            for (int j = 1; j < tabla.getColumnCount() ; j++) {

                ArrayList<SalidasPuestasDelSol> sps = new ArrayList<>();

                Date horaSalidaSol = new Date();
                Date horaPuestaSol = new Date();
                Date fechaDesde = new Date();
                Date fechaHasta = new Date();


                    if (modeloTabla.getValueAt(i, 0) != null || modeloTabla.getValueAt(i, 1) != null ||
                            modeloTabla.getValueAt(i, 2) != null || modeloTabla.getValueAt(i, 3) != null ||
                            modeloTabla.getValueAt(i, 4) != null) {

                        if (j == 1) {
                            horaSalidaSol = simpleTimeFormat.parse(String.valueOf(modeloTabla.getValueAt(i, j)));
                            horaPuestaSol = simpleTimeFormat.parse(String.valueOf(modeloTabla.getValueAt(i, j + 1)));

                            if (horaSalidaSol.after(horaPuestaSol)) {
                                mensaje.setText("¡La 'hora de salida' debe ser menor a la 'puesta del mismo' en la fila " + (i + 1) + "!");
                                return false;
                            }
                        } else if (j == 2) {
                            horaSalidaSol = simpleTimeFormat.parse(String.valueOf(modeloTabla.getValueAt(i, j - 1)));
                            horaPuestaSol = simpleTimeFormat.parse(String.valueOf(modeloTabla.getValueAt(i, j)));

                            if (horaPuestaSol.before(horaSalidaSol)) {
                                mensaje.setText("¡La 'hora de puesta' debe ser mayor a la 'salida del mismo' en la fila " + (i + 1) + "!");
                                return false;
                            }
                        } else if (j == 3) {

                                fechaDesde = simpleDateFormat.parse(String.valueOf(modeloTabla.getValueAt(i, j)));
                                fechaHasta = simpleDateFormat.parse(String.valueOf(modeloTabla.getValueAt(i, j + 1)));

                            if (fechaDesde.after(fechaHasta)) {
                                mensaje.setText("¡La 'fecha desde' debe ser menor a la 'fecha hasta' en la fila " + (i + 1) + "!");
                                return false;
                            }
                        } else{
                            fechaDesde = simpleDateFormat.parse(String.valueOf(modeloTabla.getValueAt(i, j - 1)));
                            fechaHasta = simpleDateFormat.parse(String.valueOf(modeloTabla.getValueAt(i, j)));

                            if (fechaHasta.before(fechaDesde)) {
                                mensaje.setText("¡La 'fecha hasta debe ser mayor a la 'fecha hasta' en la fila " + (i + 1) + "!");
                                return false;
                            }
                        }
                    }

            }
        }
        mensaje.setText("");

        return true;
    }

    /**
     * metodo que actualiza los datos y guarda en archivo XML
     * @throws ParseException error de formato
     */
    public void actualizarDatos() throws ParseException {

        ArrayList<SalidasPuestasDelSol> fila = new ArrayList<>();

        Date horaSalidaSol = new Date();
        Date horaPuestaSol = new Date();
        Date fechaDesde = new Date();
        Date fechaHasta = new Date();


        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (modeloTabla.getValueAt(i, 0) != null && modeloTabla.getValueAt(i, 1) != null &&
                    modeloTabla.getValueAt(i, 2) != null && modeloTabla.getValueAt(i, 3) != null &&
                    modeloTabla.getValueAt(i, 4) != null) {

                int id = Integer.parseInt(String.valueOf(modeloTabla.getValueAt(i, 0)));
                horaSalidaSol = simpleTimeFormat.parse(String.valueOf(modeloTabla.getValueAt(i, 1)));
                horaPuestaSol = simpleTimeFormat.parse(String.valueOf(modeloTabla.getValueAt(i, 2)));
                fechaDesde = simpleDateFormat.parse(String.valueOf(modeloTabla.getValueAt(i, 3)));
                fechaHasta = simpleDateFormat.parse(String.valueOf(modeloTabla.getValueAt(i, 4)));

                fila.add(new SalidasPuestasDelSol(i + 1, horaSalidaSol, horaPuestaSol, fechaDesde, fechaHasta));

            }
        }
        Balizamiento balizamiento = new Balizamiento(fila);
        Persistencia.guardarSalidasPuestasDelSol(balizamiento);

        actualizarBtn.setEnabled(false);
    }


    /**
     * metodo que carga los datos en la tabla
     * @return objeto SalidasPuestaDelSol
     */
    public ArrayList<SalidasPuestasDelSol> cargarDatos() {
        ArrayList<SalidasPuestasDelSol> fila = Persistencia.cargarBalizamiento().getSalidasPuestasDelSol();

        if(fila != null) {
            for (SalidasPuestasDelSol sps : fila) {
                Object[] datos = new Object[5];
                datos[0] = sps.getId();
                datos[1] = simpleTimeFormat.format(sps.getSalidaSol());
                datos[2] = simpleTimeFormat.format(sps.getPuestaSol());
                datos[3] = simpleDateFormat.format(sps.getFechaDesde());
                datos[4] = simpleDateFormat.format(sps.getFechaHasta());

                modeloTabla.addRow(datos);
            }
        }


        unSoloClickEnCeldas();

        return this.salidasPuestasDelSol;
    }

    /**
     * metodo que permite realizar un solo click en celda para editarlas
     */
    public void unSoloClickEnCeldas(){
        // PERMITE EDITAR CELDA CON UN SOLO CLICK
        for (int i = 0; i < modeloTabla.getRowCount() ; i++) {
            final DefaultCellEditor defaultEditor = (DefaultCellEditor) tabla.getDefaultEditor(modeloTabla.getColumnClass(i));
            defaultEditor.setClickCountToStart(1);
        }
    }

    /**
     * clase que renderiza las filas de la tabla
     */
    public class Renderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column){
            if(row % 2 == 0){
                setBackground(Color.lightGray);
                setForeground(Color.darkGray);
            }else{
                setBackground(Color.gray);
                setForeground(Color.white);
            }

            cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            return super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        }


    }

    /**
     * clase que renderia las columnas de la tabla
     */
    public class ColumnRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column){

            columnRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            return super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        }


    }

    @XmlElement
    public ArrayList<SalidasPuestasDelSol> getSalidasPuestasDelSol() {
        return salidasPuestasDelSol;
    }

    public void setSalidasPuestasDelSol(ArrayList<SalidasPuestasDelSol> salidasPuestasDelSol) {
        this.salidasPuestasDelSol = salidasPuestasDelSol;
    }
}


