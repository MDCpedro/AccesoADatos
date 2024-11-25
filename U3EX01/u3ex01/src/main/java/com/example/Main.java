package com.example;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        boolean salirMenu1 = false;
        boolean salirMenuPer = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("baseDatos.odb");

        Scanner scanner = new Scanner(System.in);

        while (!salirMenu1) {
            System.out.println("-----Base de datos-----");
            System.out.println("-------Entidades--------");
            System.out.println("1- Personas  2- Empresas");
            System.out.println("0- Salir");
            System.out.println("Teclea una opción: ");

            String opcionMenu = scanner.nextLine();
            switch (opcionMenu) {
                case "0":
                    salirMenu1 = true;
                    break;
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
                                salirMenuPer = true;
                                break;
                            case "1":
                                crearUsuario(emf);
                                break;
                            default:
                                break;
                        }

                    }
                    break;
                case "2":
                    break;
                default:
                    break;
            }

        }
    }

    public static void crearUsuario(EntityManagerFactory emf) {
        try {
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            Persona persona = new Persona("Juan2", 23, "aaaa");
            em.persist(persona);

            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            e.getMessage();
        }

    }
}