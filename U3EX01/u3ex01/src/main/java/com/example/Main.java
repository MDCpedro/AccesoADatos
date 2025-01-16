package com.example;

import java.util.Scanner;
import java.util.List;

import javax.persistence.Query;

import com.objectdb.o.STN.w;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // Booleanos de los menus para salir posteriormente
        boolean salirMenu1 = false;
        boolean salirMenuPer = false;
        boolean salirMenuEmp = false;
        // Importo la clase metodosODB para poder usar sus funciones
        metodosODB funcionesODB = new metodosODB();
        // Creo el EntityManagerFactory que nos permtirá conectarnos a la base de datos
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidadpersistencia");

        Scanner scanner = new Scanner(System.in);
        // Bucle principal del programa
        while (!salirMenu1) {
            System.out.println("-----Base de datos-----");
            System.out.println("-------Entidades--------");
            System.out.println("1- Personas  2- Empresas");
            System.out.println("0- Salir");
            System.out.println("Teclea una opción: ");

            String opcionMenu = scanner.nextLine();
            / /Swi
            System.out.printtch para elegir entre las opciones del menu principal
            switch (opcionMenu) {
                // Salir del programa
                case "0":ln("------Saliendo------");
                    salirMenu1 = true;
                    break;
                // Menu de personas
                case "1":
                    while (!salirMenuPer) {
                        System.out.println("-------Personas------");
                        System.out.println("0- Salir.");
                        System.out.println("1- Introducir Persona");
                        System.out.println("2- Borrar Persona");
                        System.out.println("3- Modificar Persona");
                        System.out.println("4- Mostrar Personas.");
                        System.out.println("Teclea una opción: ");

                        String opcionPer = scanner.nextLine();
                        switch (opcionPer) {
                            case "0":
                                // Salir del menu de personas
                                salirMenuPer = true;
                                break;
                            case "1":
                                // Crear una persona
                                funcionesODB.crearUsuario(emf, scanner);
                                break;
                            case "2":
                                // Borrar una persona
                                funcionesODB.eliminarUsuario(emf, scanner);
                                break;
                            case "3":
                                // Modificar una persona
                                funcionesODB.modificarUsuario(emf, scanner);
                                break;
                            case "4":
                                // Mostrar todas las personas
                                funcionesODB.mostrarUsuarios(emf, scanner);
                                System.out.println("Pulsa una tecla para continuar");
                                scanner.nextLine();
                                break;
                            default:
                                break;
                        }
                    }
                // Menu de empresas
                case "2":
                    while (!salirMenuEmp) {
                        System.out.println("-------Empresas------");
                        System.out.println("0- Salir.");
                        System.out.println("1- Introducir Empresa");
                        System.out.println("2- Borrar Empresa");
                        System.out.println("3- Modificar Empresa");
                        System.out.println("4- Mostrar Empresas.");
                        System.out.println("Teclea una opción: ");

                        String opcionEmp = scanner.nextLine();
                        switch (opcionEmp) {
                            case "0":
                                // Salir del menu de empresas
                                salirMenuEmp = true;
                                break;
                            case "1":
                                // Crear una empresa
                                funcionesODB.crearEmpresa(emf, scanner);
                                break;
                            case "2":
                                // Borrar una empresa
                                funcionesODB.eliminarEmpresa(emf, scanner);
                                break;
                            case "3":
                                // Modificar una empresa
                                funcionesODB.modificarEmpresa(emf, scanner);
                                break;
                            case "4":
                                // Mostrar todas las empresas
                                funcionesODB.mostrarEmpresas(emf, scanner);
                                System.out.println("Pulsa una tecla para continuar");
                                scanner.nextLine();
                                break;
                            default:
                                break;
                        }
                    }
            }
        }
    }
}