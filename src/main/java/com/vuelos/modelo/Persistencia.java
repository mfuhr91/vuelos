package com.vuelos.modelo;

import javax.swing.*;
import javax.xml.bind.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Persistencia {

    public Persistencia(){};

    public static void crearArchivos(){
        try {
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(TasaAterrizaje.class, TasaEstacionamiento.class, Vuelo.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println("Aterrizaje, estacionamiento y Valores Pax creados en: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // crear el archivo
            TasaAterrizaje aterrizaje = new TasaAterrizaje();
            TasaEstacionamiento estacionamiento = new TasaEstacionamiento();
            Vuelo vuelo = new Vuelo();

            marshaller.marshal(aterrizaje,
                    new File(archivo.getAbsolutePath() + "/tasas_aterrizaje.xml"));
            marshaller.marshal(estacionamiento,
                    new File(archivo.getAbsolutePath() + "/tasas_estacionamiento.xml"));
            marshaller.marshal(vuelo,
                    new File(archivo.getAbsolutePath() + "/valores_pax.xml"));

        } catch (PropertyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void guardarDatos(TasaAterrizaje aterrizaje, TasaEstacionamiento estacionamiento,Vuelo vuelo) {
        try {

            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(TasaAterrizaje.class, TasaEstacionamiento.class, Vuelo.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println("Aterrizaje, estacionamiento y Valores Pax actualizados en: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");


            // actualizar archivos
            marshaller.marshal(aterrizaje,
                    new File(archivo.getAbsolutePath() + "/tasas_aterrizaje.xml"));
            marshaller.marshal(estacionamiento,
                    new File(archivo.getAbsolutePath() + "/tasas_estacionamiento.xml"));
            marshaller.marshal(vuelo,
                    new File(archivo.getAbsolutePath() + "/valores_pax.xml"));
        } catch (PropertyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static TasaAterrizaje cargarAterrizaje(){
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(TasaAterrizaje.class);


            //Realiza la conversión de XML a Objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println("---------Información----------");
            System.out.println("Aterrizaje leido de: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // Se busca el fichero xml
            TasaAterrizaje aterrizaje = (TasaAterrizaje) unmarshaller.unmarshal(
                    new FileInputStream(archivo.getAbsolutePath() + "/tasas_aterrizaje.xml"));
            return aterrizaje;

        }catch(JAXBException | FileNotFoundException e){
            System.out.println("ENTRO AL ERROR ATERRIZAJE");
            int confirm = JOptionPane.showConfirmDialog(null, "Los archivos de datos NO se encuentran en la carpeta " +
                            "de la aplicación, ¿desea crear los archivos con el tarifario vacio, o prefiere ponerlos en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == 0) { // Yes en Ventana Emergente
                crearArchivos();
            } else {
               System.exit(0);
            }

        }
        return cargarAterrizaje();
    }

    public static TasaEstacionamiento cargarEstacionamiento() {
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(TasaEstacionamiento.class);


            //Realiza la conversión de XML a Objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println("Estacionamiento leido de: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // se guarda datos en fichero xml
            TasaEstacionamiento estacionamiento = (TasaEstacionamiento) unmarshaller.unmarshal(
                    new FileInputStream ( archivo.getAbsolutePath() + "/tasas_estacionamiento.xml"));

            return estacionamiento;

        }catch(JAXBException | FileNotFoundException e){
            System.out.println("ENTRO AL ERROR ESTACIONAMIENOT");
            int confirm = JOptionPane.showConfirmDialog(null, "Los archivos de tarifas NO se " +
                            "encuentran en la carpeta de la aplicación, ¿Desea crear los archivos con el tarifario vacio, o prefiere buscarlos y ponerlos en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == 0) { // Yes en Ventana Emergente
                crearArchivos();
            } else {
                System.exit(0);
            }
        }
        return cargarEstacionamiento();
    }

    public static Vuelo cargarValoresPax(){
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(Vuelo.class);


            //Realiza la conversión de XML a Objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println("Valores Pax leido de: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // se guarda datos en fichero xml
            Vuelo vuelo = (Vuelo) unmarshaller.unmarshal(
                    new FileInputStream ( archivo.getAbsolutePath() + "/valores_pax.xml"));

            return vuelo;

        }catch(JAXBException | FileNotFoundException e){
            System.out.println("ENTRO AL ERROR VALORES PAX");
            int confirm = JOptionPane.showConfirmDialog(null, "Los archivos de tarifas NO se " +
                            "encuentran en la carpeta de la aplicación, ¿Desea crear los archivos con el tarifario vacio, o prefiere buscarlos y ponerlos en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == 0) { // Yes en Ventana Emergente

                crearArchivos();
            } else {
                System.exit(0);
            }
        }
        return cargarValoresPax();
    }

}
