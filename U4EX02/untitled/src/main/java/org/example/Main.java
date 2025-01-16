package org.example;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        boolean toggleMetodos;
        Scanner sc = new Scanner(System.in);
        Transaction transaction = null;

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
                                insertarEntidad(toggleMetodos, sc);
                                break;
                            case "2":
                                test();
                        }
                    }
            }
        }
    }

    public static void insertarEntidad(boolean toggleMetodos, Scanner sc) {

        if (toggleMetodos) {
            System.out.println("----Insertando nuevo autor----");
            System.out.println("Nombre del autor: ");
            String nombreAutor = sc.nextLine();
            System.out.println("Fecha de nacimiento(YYYY-MM-DD): ");
            String fechaNacimiento = sc.nextLine();

            try {
                SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Autor.class).buildSessionFactory();
                Session session = factory.getCurrentSession();

                Autor autor = new Autor();
                autor.setNom(nombreAutor);

                LocalDate localDate = LocalDate.parse(fechaNacimiento);
                Date fecha = Date.valueOf(localDate);
                autor.setDataNaixement(localDate);

                session.beginTransaction();
                session.save(autor);
                session.getTransaction().commit();

            } catch (Exception e) {
                //transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("----Insertando nuevo libro----");
            System.out.println("Titulo del libro: ");
            String tituloLibro = sc.nextLine();
            System.out.println("Año de publicacion: ");
            int añoPublicacion = sc.nextInt();

            try {
                //transaction = session.beginTransaction();

                Llibre libro = new Llibre();
                libro.setTitol(tituloLibro);
                libro.setAnyPublicacio(añoPublicacion);
            } catch (Exception e) {
                //transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public static void ListarEntidades(Session session, boolean toggleMetodos) {
        try {
            List<Llibre> lista_libros =
                session.createQuery("SELECT l FROM Llibre l", Llibre.class).getResultList();
            for (Llibre l : lista_libros) {
                System.out.println(l.getTitol());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            Session session = factory.openSession();
            System.out.println("Conexión establecida con Hibernate");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
