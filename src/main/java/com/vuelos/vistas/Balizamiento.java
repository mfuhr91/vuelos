package com.vuelos.vistas;

import com.vuelos.modelo.Persistencia;
import com.vuelos.modelo.SalidasPuestasDelSol;
import sun.nio.cs.ext.IBM037;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement
@XmlType(propOrder = {"salidasPuestasDelSol"})
public class Balizamiento {

    Renderer cellRenderer = new Renderer();
    ColumnRenderer columnRenderer = new ColumnRenderer();
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private JPanel panelMain;
    private DefaultTableModel modeloTabla;
    private ArrayList<SalidasPuestasDelSol> salidasPuestasDelSol;
    private Object[] columnas = {"ID","Salida del Sol", "Puesta del Sol", "Fecha Desde", "Fecha Hasta"};


    public Balizamiento(ArrayList<SalidasPuestasDelSol> salidasPuestasDelSol) {
        this.salidasPuestasDelSol = salidasPuestasDelSol;
    }


    public Balizamiento() {}

    public void mostrarVentana(){
        modeloTabla = new DefaultTableModel(null,columnas);
        JTable tabla = new JTable(modeloTabla);
        GridBagConstraints formato = new GridBagConstraints();
        Font fuente = new Font("Roboto Thin", 0, 16);

        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(15);
        columnModel.getColumn(1).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(70);

        //DefaultTableCellRenderer render = (DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer();
        // render.setHorizontalAlignment(JLabel.CENTER);
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
        panelMain.add(borrarFilaBtn,formato);


        JSeparator separator = new JSeparator();
        formato.fill = GridBagConstraints.BOTH;
        formato.gridx = 0;
        formato.gridy = 10;
        formato.insets = margenSeparador;
        panelMain.add(separator, formato);

        JButton actualizarBtn = new JButton("Actualizar");
        formato.fill = GridBagConstraints.NONE;
        formato.gridx = 3;
        formato.gridy = 10;
        formato.gridwidth = 1;
        formato.gridheight = 1;
        formato.insets = margenBoton;
        formato.anchor = GridBagConstraints.SOUTHEAST;
        actualizarBtn.setFont(fuente);
        panelMain.add(actualizarBtn,formato);

        tabla.setDefaultRenderer(Object.class,cellRenderer);


        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.add(panelMain);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CAMBIAR POR .DISPOSE_ON_CLOSE
        frame.pack();
        frame.setSize(600,770);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cargarDatos();



        agregarFilaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloTabla.setRowCount(modeloTabla.getRowCount()+1);
            }
        });

        borrarFilaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = modeloTabla.getRowCount()-1; i >= 0 ; i--) {
                    if (modeloTabla.getValueAt(i, 0) == null && modeloTabla.getValueAt(i, 1) == null &&
                            modeloTabla.getValueAt(i, 2) == null && modeloTabla.getValueAt(i, 3) == null) {

                        modeloTabla.setRowCount(modeloTabla.getRowCount()-1);
                        break;

                    } else if (modeloTabla.getValueAt(i, 0) != null && modeloTabla.getValueAt(i, 1) != null &&
                            modeloTabla.getValueAt(i, 2) != null && modeloTabla.getValueAt(i, 3) != null) {

                        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere borrar la fila " + (i+1) + "?",
                                "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon("alerta.png"));
                        if (confirm == 0) { // Yes en Ventana Emergente
                            modeloTabla.setRowCount(modeloTabla.getRowCount()-1);
                            break;
                        }else{
                            break;
                        }

                    }
                }
            }
        });


        actualizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("APRETADO");
                try {
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere realizar los cambios?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon("alerta.png"));
                    if (confirm == 0) { // Yes en Ventana Emergente
                        actualizarDatos();
                        JOptionPane.showMessageDialog(null,
                                "¡Se actualizaron los datos correctamente!","Confirmación",
                                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ok.png"));
                    } else {
                        cargarDatos();
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "¡Existen celdas vacias!","Atención",
                            JOptionPane.INFORMATION_MESSAGE, new ImageIcon("alerta.png"));
                }
            }
        });

        // PERMITE EDITAR CELDA CON UN SOLO CLICK
        for (int i = 0; i < modeloTabla.getRowCount() ; i++) {
            final DefaultCellEditor defaultEditor = (DefaultCellEditor) tabla.getDefaultEditor(modeloTabla.getColumnClass(i));
            defaultEditor.setClickCountToStart(1);
        }

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

    }

    public void cargarDatos() {
        ArrayList<SalidasPuestasDelSol> fila = Persistencia.cargarBalizamiento().getSalidasPuestasDelSol();


            for (SalidasPuestasDelSol sps : fila) {
                Object[] datos = new Object[5];
                datos[0] = sps.getId();
                datos[1] = simpleTimeFormat.format(sps.getSalidaSol());
                datos[2] = simpleTimeFormat.format(sps.getPuestaSol());
                datos[3] = simpleDateFormat.format(sps.getFechaDesde());
                datos[4] = simpleDateFormat.format(sps.getFechaHasta());

                modeloTabla.addRow(datos);

                System.out.println(datos[0]);
                System.out.println(datos[1]);
                System.out.println(datos[2]);
                System.out.println(datos[3]);
                System.out.println(datos[4]);
                System.out.println();
            }
        }

    public void actualizarDatos() throws ParseException {
        ArrayList<SalidasPuestasDelSol> fila = new ArrayList<>();

        Date horaSalidaSol = new Date();
        Date horaPuestaSol = new Date();
        Date fechaDesde = new Date();
        Date fechaHasta = new Date();

        for (int i = 0; i < modeloTabla.getRowCount() ; i++) {
            if(modeloTabla.getValueAt(i,0) != null && modeloTabla.getValueAt(i,1) != null &&
                    modeloTabla.getValueAt(i,2) != null && modeloTabla.getValueAt(i,3) != null &&
                    modeloTabla.getValueAt(i,4) != null) {

                int id = Integer.parseInt(String.valueOf(modeloTabla.getValueAt(i,0)));
                horaSalidaSol = simpleTimeFormat.parse(String.valueOf(modeloTabla.getValueAt(i, 1)));
                horaPuestaSol = simpleTimeFormat.parse(String.valueOf(modeloTabla.getValueAt(i, 2)));
                fechaDesde = simpleDateFormat.parse(String.valueOf(modeloTabla.getValueAt(i, 3)));
                fechaHasta = simpleDateFormat.parse(String.valueOf(modeloTabla.getValueAt(i, 4)));

                System.out.println(id + " " + horaSalidaSol + " " + horaPuestaSol + " " + fechaDesde + " " + fechaHasta);
                fila.add(new SalidasPuestasDelSol(i + 1, horaSalidaSol, horaPuestaSol, fechaDesde, fechaHasta));
            }
        }
        Balizamiento balizamiento = new Balizamiento(fila);
        Persistencia.guardarSalidasPuestasDelSol(balizamiento);

    }

    @XmlElement
    public ArrayList<SalidasPuestasDelSol> getSalidasPuestasDelSol() {
        return salidasPuestasDelSol;
    }

    public void setSalidasPuestasDelSol(ArrayList<SalidasPuestasDelSol> salidasPuestasDelSol) {
        this.salidasPuestasDelSol = salidasPuestasDelSol;
    }




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

    public class ColumnRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column){

            columnRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            return super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        }


    }
}


