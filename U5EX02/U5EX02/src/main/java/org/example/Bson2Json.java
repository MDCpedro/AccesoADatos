package org.example;

import org.json.JSONObject;
import org.bson.Document;

public class Bson2Json {
    public JSONObject convertirBsonAJson(Document bson) {
        if (bson != null) {
            try {
                JSONObject json = new JSONObject(bson.toJson());
                System.out.println("Bson convertido correctamente a Json");
                return json;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al convertir. Puede que el formato sea incorrecto.");
                return null;
            }
        } else {
            System.out.println("El Bson no existe.");
            return null;
        }
    }
}