package com.vuelos.modelo;


import com.vuelos.vistas.Balizamiento;
import javax.swing.*;
import javax.xml.bind.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Clase que se encarga de la Persistencia de los datos.
 *
 */
public class Persistencia {

    private static String archivoAterrizaje = "tasas_aterrizaje.xml";
    private static String archivoEstacionamiento = "tasas_estacionamiento.xml";
    private static String archivoPax = "valores_pax.xml";
    private static String archivoBal = "salidas_puestas_sol.xml";


    public Persistencia(){};

    /**
     * metodo que crea el Archivo "tasas_aterrizaje.xml" vacio en caso que no se encuentre
     */
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

    /**
     * metodo que crea el Archivo "tasas_estacionamiento.xml" vacio en caso que no se encuentre
     */
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

    /**
     * metodo que crea el Archivo "valores_pax.xml" vacio en caso que no se encuentre
     */
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

    /**
     * metodo que crea el Archivo "salidas_puestas_sol.xml" vacio en caso que no se encuentre
     */
    public static void crearSalidaPuestaDelSolXML(){
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(Balizamiento.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println(archivoBal + " creado en " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // crear el archivo
            Balizamiento balizamiento = new Balizamiento();
            //SalidasPuestasDelSol salidasPuestasDelSol = new SalidasPuestasDelSol();

            marshaller.marshal(balizamiento,
                    new File(archivo.getAbsolutePath() + "/" + archivoBal));

        } catch (PropertyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * metodo que guarda los datos de la ventana Tarifario General:
     *  - Tasas de aterrizaje
     *  - Tasas de Estacionamiento
     *  - Valores de tasas de Pax
     * @param aterrizaje
     * @param estacionamiento
     * @param vuelo
     */
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
            System.out.println(archivoAterrizaje + archivoEstacionamiento +
                    archivoPax + " actualizados en: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");


            // actualizar archivos
            marshaller.marshal(aterrizaje,
                    new File(archivo.getAbsolutePath() + "/" + archivoAterrizaje));
            marshaller.marshal(estacionamiento,
                    new File(archivo.getAbsolutePath() + "/" + archivoEstacionamiento));
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

    /**
     * metodo que guarda los datos de la ventana Salidas y Puestas del Sol
     * @param balizamiento
     */
    public static void guardarSalidasPuestasDelSol(Balizamiento balizamiento){
        try {

            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(Balizamiento.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");

            System.out.println("registro con ID: " + balizamiento + " actualizados en: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");


            // actualizar archivos
            marshaller.marshal(balizamiento,
                  new File(archivo.getAbsolutePath() + "/" + archivoBal));
        } catch (PropertyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * metodo que carga los valores de Aterrizaje al abrir la ventana Tarifario General
     * @return objeto TasaAterrizaje
     */
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

            // Se lee el fichero xml
            TasaAterrizaje aterrizaje = (TasaAterrizaje) unmarshaller.unmarshal(
                    new FileInputStream(archivo.getAbsolutePath() + "/" + archivoAterrizaje));
            return aterrizaje;

        }catch(JAXBException | FileNotFoundException e){
            int confirm = JOptionPane.showConfirmDialog(null, "El archivo '" + archivoAterrizaje +
                            "' no se encuentra en la carpeta de la aplicación, " +
                            "¿Desea crear el archivo con las variables vacías, o prefiere buscarlo y ponerlo en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                    new ImageIcon(Persistencia.class.getResource("/error.png")));
            if (confirm == 0) { // Yes en Ventana Emergente
                crearTasaAterizajeXML();
            } else {
               System.exit(0);
            }

        }
        return cargarAterrizaje();
    }

    /**
     * metodo que carga los valores de Estacionamiento al abrir la ventana Tarifario General
     * @return objeto TasaEstacionamiento
     */
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

            // se lee el fichero xml
            TasaEstacionamiento estacionamiento = (TasaEstacionamiento) unmarshaller.unmarshal(
                    new FileInputStream ( archivo.getAbsolutePath() + "/" + archivoEstacionamiento));

            return estacionamiento;

        }catch(JAXBException | FileNotFoundException e){
            int confirm = JOptionPane.showConfirmDialog(null, "El archivo '" + archivoEstacionamiento +
                            "' no se encuentra en la carpeta de la aplicación, " +
                            "¿Desea crear el archivo con las variables vacías, o prefiere buscarlo y ponerlo en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                    new ImageIcon(Persistencia.class.getResource("/error.png")));
            if (confirm == 0) { // Yes en Ventana Emergente
                crearTasaEstacionamientoXML();
            } else {
                System.exit(0);
            }
        }
        return cargarEstacionamiento();
    }

    /**
     * metodo que carga los valores de tasas de pasajeros al abrir la ventana Tarifario General
     * @return objeto Vuelo
     */
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

            // se lee el fichero xml
            Vuelo vuelo = (Vuelo) unmarshaller.unmarshal(
                    new FileInputStream ( archivo.getAbsolutePath() + "/" + archivoPax));

            return vuelo;

        }catch(JAXBException | FileNotFoundException e){
            int confirm = JOptionPane.showConfirmDialog(null, "El archivo '" + archivoPax +
                            "' no se encuentra en la carpeta de la aplicación, " +
                            "¿Desea crear el archivo con las variables vacías, o prefiere buscarlo y ponerlo en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                    new ImageIcon(Persistencia.class.getResource("/error.png")));
            if (confirm == 0) { // Yes en Ventana Emergente
                crearValoresPaxXML();
            } else {
                System.exit(0);
            }
        }
        return cargarValoresPax();
    }

    /**
     * metodo que carga los datos al abrir la ventana Salidas y Puestas del Sol
     * @return objeto Balizamiento
     */
    public static Balizamiento cargarBalizamiento(){
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(Balizamiento.class);


            //Realiza la conversión de XML a Objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            //Se guarda la ruta relativa del archivo
            File archivo = new File("");
            System.out.println(archivoBal + " leido de: " + archivo.getAbsolutePath());
            System.out.println("--------------------------------------");

            // se lee el fichero xml
            Balizamiento balizamiento = (Balizamiento) unmarshaller.unmarshal(
                    new FileInputStream ( archivo.getAbsolutePath() + "/" + archivoBal));

            return balizamiento;

        }catch(JAXBException | FileNotFoundException e){
            int confirm = JOptionPane.showConfirmDialog(null, "El archivo '" + archivoBal +
                            "' no se encuentra en la carpeta de la aplicación, " +
                            "¿Desea crear el archivo con las variables vacías, o prefiere buscarlo y ponerlo en la carpeta usted mismo?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                    new ImageIcon(Persistencia.class.getResource("/error.png")));
            if (confirm == 0) { // Yes en Ventana Emergente
                crearSalidaPuestaDelSolXML();
            } else {
                System.exit(0);
            }
        }
        return cargarBalizamiento();
    }


}
