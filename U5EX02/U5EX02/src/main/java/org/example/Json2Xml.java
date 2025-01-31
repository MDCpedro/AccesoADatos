package org.example;

import org.json.JSONObject;
import org.json.XML;

public class Json2Xml {
    public String convertirJsonAXML(JSONObject json, String raizXML) {
        if (json != null) {
            try {
                String xml = XML.toString(json, raizXML);
                System.out.println("JSON convertido a XML correctamente");
                return xml;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("El JSON no se puede convertir (Quizás no tiene un formato valido");
                return null;
            }
        } else {
            System.out.println("El JSON no existe");
            return null;
        }
    }
}
