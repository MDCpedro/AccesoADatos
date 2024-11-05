package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:demo\\src\\main\\java\\com\\example\\library.db";

        try (Connection conexion = DriverManager.getConnection(url)) {
            if (conexion != null) {
                System.out.println("Conectando con base de datos");

                Statement stmt = conexion.createStatement();
                String query = "SELECT * FROM books";
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    int year = rs.getInt("year");

                    System.out.println("ID: "+id);
                    System.out.println("Titulo: "+title);
                    System.out.println("Autor: "+author);
                    System.out.println("Año: "+year);
                    System.out.println(" ");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al conectar con la base de datos");
        }
    }
}