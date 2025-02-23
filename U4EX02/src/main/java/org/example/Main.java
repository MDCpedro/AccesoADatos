package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Obtenemos una sesión de Hibernate
        Session session = Hibernate.getSession();

        boolean salir = false;
        boolean toggleMetodos; // Define si los metodos son para autores (true) o libros (false)
        Scanner sc = new Scanner(System.in);

        // Bucle principal del programa
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
                    toggleMetodos = true; // Indicamos que las operaciones son para autores
                    while (!salir1) {
                        System.out.println("----Autores----");
                        System.out.println("0- Salir");
                        System.out.println("1- Crear");
                        System.out.println("2- Eliminar");
                        System.out.println("3- Modificar"); // No he podido implementarlo aun
                        System.out.println("4- Listar");
                        String opcionAutor = sc.nextLine();

                        switch (opcionAutor) {
                            case "0":
                                salir1 = true;
                                break;
                            case "1":
                                crearEntidad(session, toggleMetodos, sc); // Metodo para crear autor
                                break;
                            case "2":
                                eliminarEntidad(session, toggleMetodos, sc); // Metodo para eliminar un autor
                                break;
                            case "3":
                                break; //  no implementado
                            case "4":
                                listarEntidades(session, toggleMetodos); // Metodo para listar autores
                                break;
                        }
                    }
                    break;
                case "2":
                    boolean salir2 = false;
                    toggleMetodos = false; // Indica que las operaciones son para libros
                    while (!salir2) {
                        System.out.println("----Libros----");
                        System.out.println("0- Salir");
                        System.out.println("1- Crear");
                        System.out.println("2- Eliminar");
                        System.out.println("3- Modificar"); // Sin implementación
                        System.out.println("4- Listar");
                        String opcionLibro = sc.nextLine();

                        switch (opcionLibro) {
                            case "0":
                                salir2 = true; //
                                break;
                            case "1":
                                crearEntidad(session, toggleMetodos, sc); // Meotodo para crear un libro
                                break;
                            case "2":
                                eliminarEntidad(session, toggleMetodos, sc); // Metodo que elimina un libro
                                break;
                            case "3":
                                break; // Modificación no implementada
                            case "4":
                                listarEntidades(session, toggleMetodos); // Meotod para listar libros
                                break;
                        }
                    }
            }
        }
    }

    // Método para crear entidades (autores o libros)
    public static void crearEntidad(Session session, boolean toggleMetodos, Scanner sc) {
        if (toggleMetodos) {
            // Creación de un autor
            System.out.println("----Insertando nuevo autor----");
            System.out.println("Nombre del autor: ");
            String nombreAutor = sc.nextLine();
            System.out.println("Fecha de nacimiento(YYYY-MM-DD): ");
            String fechaNacimiento = sc.nextLine();

            try {
                session.beginTransaction(); // Inicia la transacción

                Autor autor = new Autor();
                autor.setNom(nombreAutor);
                LocalDate localdate = LocalDate.parse(fechaNacimiento); // Convierte la fecha
                autor.setDataNaixement(localdate);

                session.save(autor); // Guarda el autor
                session.getTransaction().commit(); // Confirma la transacción

                System.out.println("Autor guardado");
            } catch (Exception e) {
                e.printStackTrace();
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback(); // Reversa en caso de error
                }
            }
        } else {
            // Creación de un libro
            System.out.println("----Insertando nuevo libro----");
            System.out.println("Titulo del libro: ");
            String tituloLibro = sc.nextLine();
            System.out.println("Año de publicacion: ");
            int añoPublicacion = sc.nextInt();
            sc.nextLine();

            listarEntidades(session, true); // Lista autores para seleccionar
            System.out.println("Introduce el id del autor: ");
            int idAutor = sc.nextInt();
            sc.nextLine();

            try {
                session.beginTransaction();

                Llibre llibre = new Llibre();
                llibre.setTitol(tituloLibro);
                llibre.setAnyPublicacio(añoPublicacion);
                llibre.setAutor(session.get(Autor.class, idAutor)); // Asocia el autor
                session.save(llibre);
                session.getTransaction().commit();

                System.out.println("Libro guardado");
            } catch (Exception e) {
                e.printStackTrace();
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
            }
        }
    }

    // Método para eliminar entidades (autores o libros)
    public static void eliminarEntidad(Session session, boolean toggleMetodos, Scanner sc) {
        if (toggleMetodos) {
            // Elimina un autor
            System.out.println("----Eliminando autor----");
            listarEntidades(session, toggleMetodos); // Lista autores
            System.out.println("Introduce el id del autor a eliminar: ");
            int id = sc.nextInt();
            sc.nextLine();

            try {
                session.beginTransaction();
                Autor autor = session.get(Autor.class, id); // Recupera el autor
                if (autor != null) {
                    session.delete(autor); // Elimina el autor
                    System.out.println("El autor: " + autor.getNom() + " ha sido eliminado");
                } else {
                    System.out.println("Este autor no existe");
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
            }
        } else {
            // Elimina un libro
            System.out.println("----Eliminando libro----");
            System.out.println("Introduce el id del libro a eliminar: ");
            int id = sc.nextInt();
            sc.nextLine();

            try {
                session.beginTransaction();
                Llibre llibre = session.get(Llibre.class, id); // Recupera el libro
                if (llibre != null) {
                    session.delete(llibre); // Elimina el libro
                    System.out.println("El libro: " + llibre.getTitol() + " ha sido eliminado");
                } else {
                    System.out.println("Este libro no existe");
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
            }
        }
    }

    // Método para listar entidades (autores o libros)
    public static void listarEntidades(Session session, boolean toggleMetodos) {
        if (toggleMetodos) {
            // Lista autores
            System.out.println("----Listando autores----");
            session.beginTransaction();
            try {
                List<Autor> lista_autores = session.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
                for (Autor a : lista_autores) {
                    System.out.println("ID: " + a.getId() + "| Nombre: " + a.getNom());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Lista libros
            System.out.println("----Listando Libros----");
            try {
                List<Llibre> lista_libros = session.createQuery("SELECT l FROM Llibre l", Llibre.class).getResultList();
                for (Llibre l : lista_libros) {
                    System.out.println(l.getTitol());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
