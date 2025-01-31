package org.example;

import org.json.JSONObject;
import org.json.XML;

public class Xml2Json {
    public JSONObject convertirXmlAJson(String xml) {
        if (xml != null) {
            try {
                JSONObject json = XML.toJSONObject(xml);
                System.out.println("XML Convertido a JSON correctamente");
                return json;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("El XML no tiene un formato valido");
                return null;
            }
        } else {
            System.out.println("El XML no existe");
            return null;
        }
    }
}
