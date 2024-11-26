package com.example;

import java.util.Scanner;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        boolean salirMenu1 = false;
        boolean salirMenuPer = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidadpersistencia");

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
                                System.out.println("------Saliendo------");
                                salirMenuPer = true;
                                break;
                            case "1":
                                crearUsuario(emf, scanner);
                                break;
                            case "2":
                                eliminarUsuario(emf, scanner);
                                break;
                            case "4":
                                mostrarUsuarios(emf);
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

    public static void crearUsuario(EntityManagerFactory emf, Scanner scanner) {
        try {
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            System.out.println("-----Creando usuario-----");
            System.out.println("Introduce el nombre: ");
            String nombre = scanner.nextLine();
            System.out.println("Introduce la edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Introduce correo electronico: ");
            String correo = scanner.nextLine();
            
            Persona persona = new Persona(nombre, edad, correo);
            em.persist(persona);

            em.getTransaction().commit();
            System.out.println("Usuario guardado correctamente (pulsa una tecla para continuar)");
            scanner.nextLine();
            em.close();

        } catch (Exception e) {
            System.out.println("Errir al crear usuario");
            e.getMessage();
            e.printStackTrace();
        }

    }

    public static void eliminarUsuario(EntityManagerFactory emf, Scanner scanner) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            System.out.println("------Eliminando usuario-------");
            System.out.println("Escribe el nombre del usuario a eliminar");
            String nombre = scanner.nextLine();
            Persona persona = em.find(Persona.class, nombre);

            if (persona != null) {
                em.remove(persona);
                System.out.println("Usuario eliminado.");
            } else {
                System.out.println("El usuario con el nombre: " +nombre+ ", no existe");
            }
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            System.out.println("Error al borrar usuario");
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void mostrarUsuarios(EntityManagerFactory emf) {

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Query queryUsers = em.createQuery("SELECT p FROM Persona p");
            List<Persona> listaUsuarios = queryUsers.getResultList();

            for(Persona usuario : listaUsuarios) {
                System.out.println(usuario.printPersona());
            }

            em.getTransaction().commit();
            em.close();
            
        } catch (Exception e) {
            System.out.println("Error al mostrar los usuarios");
            e.getMessage();
            e.printStackTrace();
        }
    }
}