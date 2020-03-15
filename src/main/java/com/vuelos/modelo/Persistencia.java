package com.vuelos.modelo;

import javax.swing.*;
import javax.xml.bind.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Persistencia {

    private static String archivoAterrizaje = "tasas_aterrizaje.xml";
    private static String archivoEstacionamiento = "tasas_estacionamiento.xml";
    private static String archivoPax = "valores_pax.xml";
    private static String archivoSol = "salidas_puestas_sol.xml";


    public Persistencia(){};


    public static void crearTasaAterizajeXML(){
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(TasaAterrizaje.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println( archivoAterrizaje + " creado en: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // crear el archivo
            TasaAterrizaje aterrizaje = new TasaAterrizaje();

            marshaller.marshal(aterrizaje,
                    new File(archivo.getAbsolutePath() + "/" + archivoAterrizaje ));

        } catch (PropertyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void crearTasaEstacionamientoXML(){
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(TasaEstacionamiento.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println( archivoEstacionamiento + " creado en: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // crear el archivo
            TasaEstacionamiento estacionamiento = new TasaEstacionamiento();

            marshaller.marshal(estacionamiento,
                    new File(archivo.getAbsolutePath() + "/" + archivoEstacionamiento));

        } catch (PropertyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void crearValoresPaxXML(){
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(Vuelo.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println(archivoPax + " creado en: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // crear el archivo
            Vuelo vuelo = new Vuelo();

            marshaller.marshal(vuelo,
                    new File(archivo.getAbsolutePath() + "/" + archivoPax));

        } catch (PropertyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void crearSalidaPuestaDelSolXML(){
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(SalidasPuestasDelSol.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println(archivoSol + " creado en " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // crear el archivo
            SalidasPuestasDelSol salidasPuestasDelSol = new SalidasPuestasDelSol();

            marshaller.marshal(salidasPuestasDelSol,
                    new File(archivo.getAbsolutePath() + "/" + archivoSol));

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
            JAXBContext contexto = JAXBContext.newInstance(TasaAterrizaje.class, TasaEstacionamiento.class, Vuelo.class, SalidasPuestasDelSol.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println(archivoAterrizaje + archivoEstacionamiento +
                    archivoPax + archivoSol + " actualizados en: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");


            // actualizar archivos
            marshaller.marshal(aterrizaje,
                    new File(archivo.getAbsolutePath() + "/" + archivoAterrizaje));
            marshaller.marshal(estacionamiento,
                    new File(archivo.getAbsolutePath() + "/" + archivoEstacionamiento));
            marshaller.marshal(vuelo,
                    new File(archivo.getAbsolutePath() + "/" + archivoPax));
            marshaller.marshal(vuelo,
                    new File(archivo.getAbsolutePath() + "/" + archivoSol));
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
            System.out.println(archivoAterrizaje + " leido de: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // Se busca el fichero xml
            TasaAterrizaje aterrizaje = (TasaAterrizaje) unmarshaller.unmarshal(
                    new FileInputStream(archivo.getAbsolutePath() + "/" + archivoAterrizaje));
            return aterrizaje;

        }catch(JAXBException | FileNotFoundException e){
            int confirm = JOptionPane.showConfirmDialog(null, "Los archivos de datos NO se encuentran en la carpeta " +
                            "de la aplicación, ¿desea crear los archivos con el tarifario vacio, o prefiere ponerlos en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == 0) { // Yes en Ventana Emergente
                crearTasaAterizajeXML();
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
            System.out.println(archivoEstacionamiento +  " leido de: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // se guarda datos en fichero xml
            TasaEstacionamiento estacionamiento = (TasaEstacionamiento) unmarshaller.unmarshal(
                    new FileInputStream ( archivo.getAbsolutePath() + "/" + archivoEstacionamiento));

            return estacionamiento;

        }catch(JAXBException | FileNotFoundException e){
            int confirm = JOptionPane.showConfirmDialog(null, "Los archivos de tarifas NO se " +
                            "encuentran en la carpeta de la aplicación, ¿Desea crear los archivos con el tarifario vacio, o prefiere buscarlos y ponerlos en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == 0) { // Yes en Ventana Emergente
                crearTasaEstacionamientoXML();
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
            System.out.println(archivoPax + " leido de: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // se guarda datos en fichero xml
            Vuelo vuelo = (Vuelo) unmarshaller.unmarshal(
                    new FileInputStream ( archivo.getAbsolutePath() + "/" + archivoPax));

            return vuelo;

        }catch(JAXBException | FileNotFoundException e){
            int confirm = JOptionPane.showConfirmDialog(null, "Los archivos de tarifas NO se " +
                            "encuentran en la carpeta de la aplicación, ¿Desea crear los archivos con el tarifario vacio, o prefiere buscarlos y ponerlos en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == 0) { // Yes en Ventana Emergente
                crearValoresPaxXML();
            } else {
                System.exit(0);
            }
        }
        return cargarValoresPax();
    }
}
