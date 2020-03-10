package com.vuelos.modelo;

import org.w3c.dom.ls.LSOutput;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;


public class Persistencia {

    public Persistencia(){};

    public static void guardarDatos(TasaAterrizaje aterrizaje, TasaEstacionamiento estacionamiento) throws JAXBException {
        try {

            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(TasaAterrizaje.class, TasaEstacionamiento.class);

            // Realiza la conversión de los Objetos a XML
            Marshaller marshaller = contexto.createMarshaller();

            // Prepara el formato del archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // crear el archivo o imprime
            marshaller.marshal(aterrizaje, new File("/Users/mariano/IdeaProjects/vuelos/tasas_aterrizaje.xml"));
            marshaller.marshal(estacionamiento, new File("/Users/mariano/IdeaProjects/vuelos/tasas_estacionamiento.xml"));
        } catch (PropertyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void cargarDatos() throws JAXBException{
        try{
            //CONTEXTO
            JAXBContext contexto = JAXBContext.newInstance(TasaAterrizaje.class, TasaEstacionamiento.class);

            //Realiza la conversión de XML a Objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            //C
            TasaAterrizaje aterrizaje = (TasaAterrizaje) unmarshaller.unmarshal(
                    new File ("/Users/mariano/IdeaProjects/vuelos/tasas_aterrizaje.xml"));
            TasaEstacionamiento estacionamiento = (TasaEstacionamiento) unmarshaller.unmarshal(
                    new File ("/Users/mariano/IdeaProjects/vuelos/tasas_estacionamiento.xml"));

        }catch(JAXBException e){
            e.printStackTrace();
        }
    }
}
