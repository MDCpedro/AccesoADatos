package com.example;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.bson.Document;
import org.json.JSONObject;
import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MongoCollection<Document> coleccion = null;
        String mongoUrl = "mongodb+srv://user:passguord@cluster0.to9ja.mongodb.net/";
        String dataBaseName = "AD2025";

        // Intentamos conectarnos a la base de datos
        try (MongoClient cliente = MongoClients.create(mongoUrl)) {
            // Obtenemos la base de datos
            MongoDatabase baseDatos = cliente.getDatabase(dataBaseName);
            System.out.println("Se ha conectado a " + baseDatos.getName());

            // Pedimos al usuario que especifique la colección de MongoDB 
           
            System.out.println("ESPECIFIQUE LA COLECCIÓN DE LA BASE DE DATOS");
            String nombreColeccion = scanner.nextLine();
            coleccion = baseDatos.getCollection(nombreColeccion);

            // Obtenemos los documentos de la colección
            FindIterable<Document> resultados = coleccion.find(new Document());
            
            System.out.println("Obteniendo los documentos de la base de datos");

            // Iteramos sobre los documentos
            int id_documento = 1;
            for (Document elemento : resultados) {
                // Convertimos el documento a JSONObject
                JSONObject json = new JSONObject(elemento.toJson());
                // Convertimos el JSON a XML
                String xml = JsonAXML(json);
                System.out.println(xml);
                System.out.println("-----------------------------");
                // Guardamos el XML en un archivo
                String nombreXML = "documento" + id_documento + ".xml";
                try (FileWriter escritor = new FileWriter(nombreXML)) {
                    escritor.write(xml);
                }
                id_documento++;
            }
            
        } catch (Exception e) {
            e.printStackTrace(); 
            System.err.println("Error al conectar a la base de datos");
        }
    }
    // Método para convertir un JSON a XML
    public static String JsonAXML(JSONObject json) {
        if (json != null) {
            try {
                // Convertimos el JSON a XML
                String xml = org.json.XML.toString(json);
                System.out.println("JSON convertido a XML correctamente");
                // Retornamos el XML
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