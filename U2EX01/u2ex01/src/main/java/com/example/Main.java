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
        System.out.println("Conectando con la base de datos...");

        // Conexión a la base de datos
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root",
                "cide2050")) {
            // creamos un scanner, un booleano para salir del bucle y un statement para
            // ejecutar las consultas
            Scanner scanner = new Scanner(System.in);
            boolean salir = false;
            Statement stmt = conexion.createStatement();
            // bucle mostrando el menu y recibiendo la opción del usuario
            while (!salir) {

                System.out.println("-------------Base de datos--------------");
                System.out.println("---------------Empleados----------------");
                System.out.println("");
                System.out.println("--------------Bienvenido----------------");
                System.out.println("0- Salir.");
                System.out.println("1- Leer Base de datos.");
                System.out.println("2- Insertar en Base de datos.");
                System.out.println("Teclea el numero para seleccionar:");

                String opcion = scanner.nextLine();
                // switch para seleccionar la opción
                switch (opcion) {
                    case "0":
                        // si la opción es 0, salimos del bucle
                        salir = true;
                        break;
                    case "1":
                        // llamamos a la función leerBaseDatos
                        leerBaseDatos(conexion);
                        System.out.println("Pulsa cualquier tecla para continuar.");
                        scanner.nextLine();
                        break;
                    case "2":
                        // llamamos a la función insertarBdd
                        insertarBdd(conexion, scanner);
                        System.out.println("Pulsa cualquier tecla para continuar.");
                        scanner.nextLine();
                        break;
                    default:
                        // mensaje de error si la opción no es válida
                        System.out.println("----------------Error----------------");
                        System.out.println("Opción no válida, teclea una disponible.");
                        System.out.println("-------------------------------------");
                        System.out.println("Pulsa cualquier tecla para continuar.");
                        scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al conectar con la base de datos");
        }
    }

    // función para leer la base de datos
    public static void leerBaseDatos(Connection conexion) {
        System.out.println("Leyendo base de datos...");

        try {
            // creamos un statement y ejecutamos la query
            Statement stmt = conexion.createStatement();
            String query = "SELECT * FROM empleats";
            ResultSet rs = stmt.executeQuery(query);
            // bucle para mostrar los datos
            while (rs.next()) {
                // guardamos los datos en variables
                int id = rs.getInt("id");
                String nombre = rs.getString("nom");
                int edad = rs.getInt("edat");
                String correo = rs.getString("correu");
                // mostramos los datos
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Edad: " + edad);
                System.out.println("Correo: " + correo);
                System.out.println("------------------------");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al leer la base de datos");
        }

    }

    // función para insertar en la base de datos
    public static void insertarBdd(Connection conexion, Scanner scanner) {
        boolean salirInsert = false;
        String opcionInsert;
        // bucle para insertar empleados
        while (!salirInsert) {
            System.out.println("Insertando nuevo empleado.");
            System.out.println("---------------------------");

            System.out.println("Introduce Nombre Completo: ");
            String nombre = scanner.nextLine();

            System.out.println("Introduce Edad: ");
            int edad = scanner.nextInt();

            System.out.println("Introduce Correo: ");
            String correo = scanner.nextLine();
            // query para insertar los datos de los scanner
            String insert = "INSERT INTO empleats (nom, edat, correu) VALUES (?, ?, ?)";
            // try para ejecutar la query
            try (PreparedStatement prstmt = conexion.prepareStatement(insert)) {
                // sustituimos los ? por los valores de los scanner
                prstmt.setString(1, nombre);
                prstmt.setInt(2, edad);
                prstmt.setString(3, correo);
                prstmt.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Error al crear usuario.");
            }

            boolean opcionValida = false;
            // bucle para salir del bucle de insertar empleados
            while (!opcionValida) {
                System.out.println("Usuario creado con exito. Desea añadir otro?");
                System.out.println("Y - si | N - no");
                opcionInsert = scanner.nextLine();
                // si la opción es no, salimos del bucle
                if (opcionInsert.equalsIgnoreCase("N") || opcionInsert.equalsIgnoreCase("no")) {
                    salirInsert = true;
                    opcionValida = true;
                    // si la opción es si, seguimos insertando empleados
                } else if (opcionInsert.equalsIgnoreCase("Y") || opcionInsert.equalsIgnoreCase("si")) {
                    opcionValida = true;
                } else {
                    System.out.println("Opcion no valida.");
                }
            }
        }
    }
}

// comprobar en todos los scanners que los valores son correctos o petará
