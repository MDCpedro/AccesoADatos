package com.example;

import java.sql.Statement;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Ruta de la base de datos
        String url = "jdbc:sqlite:u2ex01/src/main/java/com/example/BDD.sqlite";

        System.out.println("Conectando con la base de datos...");

        // Conexión a la base de datos
        try (Connection conexion = DriverManager.getConnection(url)) {
            
            Scanner scanner = new Scanner(System.in);
            boolean salir = false;

            Statement stmt = conexion.createStatement();
            String query = "SELECT * FROM empleats";
            ResultSet rs = stmt.executeQuery(query);
            
            while (salir != true) {

                menu();
                int opcion = scanner.nextInt();  
                scanner.nextLine();

                if (opcion == 0) {
                    salir = true;
                } else if (opcion == 1) {
                    leerBaseDatos(rs);
                    System.out.println("Pulsa cualquier tecla para continuar.");
                    scanner.nextLine();
                } else if (opcion == 2) {
                    insertarBdd(conexion, scanner);
                    System.out.println("Pulsa cualquier tecla para continuar.");
                    scanner.nextLine();
                } else {
                    error();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al conectar con la base de datos");
        }
    }

    public static void menu() {
        System.out.println("-------------Base de datos--------------");
        System.out.println("---------------Empleados----------------");
        System.out.println("");
        System.out.println("--------------Bienvenido----------------");
        System.out.println("Teclea el numero para seleccionar:");
        System.out.println("0- Salir.");
        System.out.println("1- Leer Base de datos.");
        System.out.println("2- Insertar en Base de datos.");
    }

    public static void error() {
        System.out.println("----------------Error----------------");
        System.out.println("Opción no válida, teclea una en pantalla.");
        System.out.println("-------------------------------------");
    }

    public static void leerBaseDatos(ResultSet rs) {
        System.out.println("Leyendo base de datos...");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nom");
                int edad = rs.getInt("edat");
                String correo = rs.getString("correu");
    
                System.out.println("ID: "+id);
                System.out.println("Nombre: "+nombre);
                System.out.println("Edad: "+edad);
                System.out.println("Correo: "+correo);
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al leer la base de datos");
        }
    }

    public static void insertarBdd(Connection conexion, Scanner scanner) {
        System.out.println("Insertando nuevo empleando.");

        System.out.println("Introduce Nombre Completo: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduce Edad: ");
        int edad = scanner.nextInt();
        System.out.println("Introduce Correo: ");
        String correo = scanner.nextLine();

        String insert = "INSERT INTO empleats (nom, edat, correu) VALUES (?, ?, ?)";

        try (PreparedStatement prstmt = conexion.prepareStatement(insert)) {
            prstmt.setString(1, nombre);
            prstmt.setInt(2, edad);
            prstmt.setString(3, correo);
            prstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al crear usuario.");
        }
    }
}