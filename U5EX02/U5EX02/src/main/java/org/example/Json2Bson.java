package org.example;

import org.json.JSONObject;
import org.bson.Document;

import javax.print.Doc;

public class Json2Bson {
    public Document convertirJsonABson(JSONObject json) {
        if (json != null) {
            try {
                Document bson = Document.parse(json.toString());
                System.out.println("Json convertido a Bson correctamente.");
                return bson;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al convertir. Puede que el formato sea incorrecto.");
                return null;
            }
        } else {
            System.out.println("El Json no existe.");
            return null;
        }
    }
}
