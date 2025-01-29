package org.example;
import org.json.JSONObject;
import org.json.XML;

public class Xml2Json {
    public JSONObject convertirXmlAJson(String xml) {
        try {
            JSONObject json = XML.toJSONObject(xml);
            System.out.println("XML Convertido a JSON correctamente");
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El XML no se puede convertir (Quizás no tiene un formato valido)");
            return null;
        }
    }
}
