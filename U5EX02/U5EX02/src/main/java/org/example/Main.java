package org.example;

import org.json.JSONObject;
import org.json.XML;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path pathXml1 = Path.of("src/main/resources/archivoXML1.xml");
        Path pathJson1 = Path.of("src/main/resources/archivoJSON1.json");
        Path pathXml2 = Path.of("src/main/resources/archivoXML2.xml");
        Xml2Json XMLaJSON = new Xml2Json();
        Json2Xml JSONaXML = new Json2Xml();

        try {
            //leemos el xml, lo convertimos a objeto Json
            String xml1 = Files.readString(pathXml1);
            JSONObject json1 = XMLaJSON.convertirXmlAJson(xml1);

            //Comprobamos y guardamos el json en un archivo.
            if (json1 != null) {
                Files.write(pathJson1, json1.toString().getBytes());

                //Leemos el json y lo convertimos a xml
                String json1Contenido = Files.readString(pathJson1);
                JSONObject json2 = new JSONObject(json1Contenido);
                String json2Contenido = JSONaXML.convertirJsonAXML(json2, null);

                //Guardamos el XML convertido y lo imprimimos por consola
                Files.write(pathXml2, json2Contenido.getBytes());
                String xml2Contenido = Files.readString(pathXml2);

                //imprimimos el fichero original y el nuevo.
                System.out.println("Fichero Original:");
                System.out.println(xml1);
                System.out.println("Fichero nuevo:");
                System.out.println(xml2Contenido);

            } else {
                System.out.println("No se ha podido leer el archivo XML");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo XML, es posible que este no exista");
        }
    }
}