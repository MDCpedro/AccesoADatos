package org.example;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Session session = Hibernate.getSession();
        boolean salir = false;
        boolean toggleMetodos;
        Scanner sc = new Scanner(System.in);

        while (!salir) {
            System.out.println("-----Biblioteca-----");
            System.out.println("0- Salir");
            System.out.println("1- Autores");
            System.out.println("2- Libros");
            System.out.println("Elije una opcion:");

            String opcion1 = sc.nextLine();

            switch (opcion1) {
                case "1":
                    boolean salir1 = false;
                    toggleMetodos = true;
                    while (!salir1) {
                        System.out.println("----Autores----");
                        System.out.println("1- Crear");
                        System.out.println("2- Eliminar");
                        System.out.println("3- Modificar");
                        System.out.println("4- Listar");
                        String opcionAutor = sc.nextLine();

                        switch (opcionAutor) {
                            case "1":

                                break;
                            case "2":

                        }
                    }
            }
        }

        public static void crearEntidad(Session session) {
            try {
                session.beginTransaction();

                Autor autor = new Autor();
                autor.setNom("Manu parte2");
                String fechaNacimiento = "2000-12-12";
                LocalDate localdate = LocalDate.parse(fechaNacimiento);
                autor.setDataNaixement(localdate);

                session.save(autor);
                session.getTransaction().commit();
                System.out.println("Autor guardado");
            } catch (Exception e) {
                e.printStackTrace();
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
            } finally {
                session.close();
                Hibernate.shutdown();
            }
        }


    }
}
