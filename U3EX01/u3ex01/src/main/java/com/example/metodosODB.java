package com.example;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class metodosODB {
// Metodo para crear usuario le pasamos el EntityManagerFactory y el Scanner
    public void crearUsuario(EntityManagerFactory emf, Scanner scanner) {
        try {
            // Creamos el EntityManager
            EntityManager em = emf.createEntityManager();
            // Iniciamos la transacción
            em.getTransaction().begin();
            // Proceso para el usuario de introducir los datos
            System.out.println("-----Creando usuario-----");
            System.out.println("Introduce el nombre: ");
            String nombre = scanner.nextLine();
            System.out.println("Introduce la edad: ");  
            int edad = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Introduce correo electronico: ");
            String correo = scanner.nextLine();
            
            // Creamos el objeto persona con los datos introducidos y lo guardamos en la base de datos
            Persona persona = new Persona(nombre, edad, correo);
            em.persist(persona);
            em.getTransaction().commit();

            System.out.println("Usuario guardado correctamente (pulsa una tecla para continuar)");
            scanner.nextLine();
            em.close();

        } catch (Exception e) {
            System.out.println("--------------------");
            System.out.println("Error al crear usuario.");
            System.out.println("Debe introducir un número en el campo edad.");
        }

    }
    // Metodo para eliminar usuario 
    public void eliminarUsuario(EntityManagerFactory emf, Scanner scanner) {
        try {
            // Creamos el EntityManager
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            // Proceso para eliminar usuario
            System.out.println("------Eliminando usuario-------");
            mostrarUsuarios(emf, scanner);
            System.out.println("Escribe el nombre del usuario a eliminar");
            String nombre = scanner.nextLine();
            // Buscamos el usuario por el nombre mediante una query, he puesto LOWER para que no distinga entre mayusculas y minusculas, si no encuentra el usuario devuelve null
            Persona persona = em.createQuery("SELECT p FROM Persona p WHERE LOWER(p.nombre) = LOWER(:nombre)", Persona.class).setParameter("nombre", nombre)
            .getResultList().stream().findFirst().orElse(null);
            // Si encontramos el usuario lo eliminamos, si no mostramos un mensaje de que no existe
            if (persona != null) {
                em.remove(persona);
                System.out.println("Usuario eliminado.");
            } else {
                System.out.println("El usuario con el nombre: " +nombre+ ", no existe");
            }
            // Finalizamos la transacción y cerramos el EntityManager
            em.getTransaction().commit();
            em.close();

            System.out.println("Pulsa una tecla para continuar");
            scanner.nextLine();

        } catch (Exception e) {
            System.out.println("Error al borrar usuario");
            e.getMessage();
            e.printStackTrace();
        }
    }
    // Metodo para mostrar los usuarios
    public void mostrarUsuarios(EntityManagerFactory emf, Scanner scanner) {

        try {
            // Creamos el EntityManager
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            // Usamos una query para seleccionar toda la tabla, y guardamos esta informacion (que son objetos de tipo Persona) en una lista
            Query queryUsers = em.createQuery("SELECT p FROM Persona p");
            List<Persona> listaUsuarios = queryUsers.getResultList();
            // Recorremos la lista de usuarios y mostramos la información de cada uno mediante el metodo printPersona
            for(Persona usuario : listaUsuarios) {
                System.out.println(usuario.printPersona());
            }
            // Finalizamos la transacción y cerramos el EntityManager
            em.getTransaction().commit();
            em.close();
        
        } catch (Exception e) {
            System.out.println("Error al mostrar los usuarios");
            e.getMessage();
            e.printStackTrace();
        }
    }
    // Metodo para modificar usuario
    public void modificarUsuario(EntityManagerFactory emf, Scanner scanner) {
        try {
            // Creamos el EntityManager
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            // Proceso para modificar usuario
            System.out.println("------Modificando usuario-------");
            mostrarUsuarios(emf, scanner);
            System.out.println("Escribe el nombre del usuario a modificar");
            String nombre = scanner.nextLine();

            // Buscamos el usuario como en el metodo eliminarUsuario
            Persona persona = em.createQuery("SELECT p FROM Persona p WHERE LOWER(p.nombre) = LOWER(:nombre)", Persona.class).setParameter("nombre", nombre)
            .getResultList().stream().findFirst().orElse(null);
            // Si se encuentra el usuario, pide los nuevos datos y los guardamos en la base de datos
            if (persona != null) {
                System.out.println("Introduce el nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                System.out.println("Introduce la nueva edad: ");
                int nuevaEdad = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduce el nuevo correo electronico: ");
                String nuevoCorreo = scanner.nextLine();

                persona.setNombre(nuevoNombre);
                persona.setEdad(nuevaEdad);
                persona.setEmail(nuevoCorreo);
                // Guardamos los cambios
                em.persist(persona);
                System.out.println("Usuario modificado.");
            } else {
                System.out.println("El usuario con el nombre: " +nombre+ ", no existe");
            }
            // Finalizamos la transacción y cerramos el EntityManager
            em.getTransaction().commit();
            em.close();

            System.out.println("Pulsa una tecla para continuar");
            scanner.nextLine();

        } catch (Exception e) {
            System.out.println("Error al modificar usuario");
            e.getMessage();
            e.printStackTrace();
        }
    }
    // Metodos para empresas
    // Metodo para crear empresa
    public void crearEmpresa(EntityManagerFactory emf, Scanner scanner) {
        try {
            // Creamos el EntityManager
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            // Proceso para crear empresa
            System.out.println("-----Creando empresa-----");
            System.out.println("Introduce el cif: ");
            String cif = scanner.nextLine();
            System.out.println("Introduce el nombre: ");
            String nombre = scanner.nextLine();
            System.out.println("Introduce la dirección: ");
            String direccion = scanner.nextLine();
            // Creamos el objeto empresa con los datos introducidos y lo guardamos en la base de datos
            Empresa empresa = new Empresa(cif, nombre, direccion);
            em.persist(empresa);

            em.getTransaction().commit();
            System.out.println("Empresa guardada correctamente (pulsa una tecla para continuar)");
            scanner.nextLine();
            em.close();

        } catch (Exception e) {
            System.out.println("--------------------");
            System.out.println("Error al crear empresa.");
            e.getMessage();
            e.printStackTrace();
        }
    }
    // Metodo para eliminar empresa
    public void eliminarEmpresa(EntityManagerFactory emf, Scanner scanner) {
        try {
            // Creamos el EntityManager
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            // Proceso para eliminar empresa
            System.out.println("------Eliminando empresa-------");
            mostrarEmpresas(emf, scanner);
            System.out.println("Escribe el nombre de la empresa a eliminar");
            String nombre = scanner.nextLine();
            // Buscamos la empresa por el nombre mediante una query, he puesto LOWER para que no distinga entre mayusculas y minusculas, si no encuentra la empresa devuelve null
            Empresa empresa = em.createQuery("SELECT e FROM Empresa e WHERE LOWER(e.nombre) = LOWER(:nombre)", Empresa.class).setParameter("nombre", nombre)
            .getResultList().stream().findFirst().orElse(null);
            // Si encontramos la empresa la eliminamos, si no mostramos un mensaje de que no existe
            if (empresa != null) {
                em.remove(empresa);
                System.out.println("Empresa eliminada.");
            } else {
                System.out.println("La empresa con el nombre: " +nombre+ ", no existe");
            }
            // Finalizamos la transacción y cerramos el EntityManager
            em.getTransaction().commit();
            em.close();

            System.out.println("Pulsa una tecla para continuar");
            scanner.nextLine();

        } catch (Exception e) {
            System.out.println("Error al borrar empresa");
            e.getMessage();
            e.printStackTrace();
        }
    }
    // Metodo para mostrar empresas
    public void mostrarEmpresas(EntityManagerFactory emf, Scanner scanner) {
        
        try {
            // Creamos el EntityManager
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            // Usamos una query para seleccionar toda la tabla, y guardamos esta informacion (que son objetos de tipo Empresa) en una lista
            Query queryEmpresas = em.createQuery("SELECT e FROM Empresa e");
            List<Empresa> listaEmpresas = queryEmpresas.getResultList();
            // Recorremos la lista de empresas y mostramos la información de cada una mediante el metodo printEmpresa
            for(Empresa empresa : listaEmpresas) {
                System.out.println(empresa.printEmpresa());
            }
            // Finalizamos la transacción y cerramos el EntityManager
            em.getTransaction().commit();
            em.close();
            
        } catch (Exception e) {
            System.out.println("Error al mostrar las empresas");
            e.getMessage();
            e.printStackTrace();
        }
    }
    // Metodo para modificar empresa
    public void modificarEmpresa(EntityManagerFactory emf, Scanner scanner) {
        try {
            // Creamos el EntityManager
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            // Proceso para modificar empresa
            System.out.println("------Modificando empresa-------");
            mostrarEmpresas(emf, scanner);
            System.out.println("Escribe el nombre de la empresa a modificar");
            String nombre = scanner.nextLine();
            // Buscamos la empresa por el nombre mediante una query, he puesto LOWER para que no distinga entre mayusculas y minusculas, si no encuentra la empresa devuelve null
            Empresa empresa = em.createQuery("SELECT e FROM Empresa e WHERE LOWER(e.nombre) = LOWER(:nombre)", Empresa.class).setParameter("nombre", nombre)
            .getResultList().stream().findFirst().orElse(null);
            // Si se encuentra la empresa, pide los nuevos datos y los guardamos en la base de datos
            if (empresa != null) {
                System.out.println("Introduce el nuevo cif: ");
                String nuevoCif = scanner.nextLine();
                System.out.println("Introduce el nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                System.out.println("Introduce la nueva dirección: ");
                String nuevaDireccion = scanner.nextLine();

                empresa.setCif(nuevoCif);
                empresa.setNombre(nuevoNombre);
                empresa.setDireccion(nuevaDireccion);
                // Guardamos los cambios
                em.persist(empresa);
                System.out.println("Empresa modificada.");
            } else {
                System.out.println("La empresa con el nombre: " +nombre+ ", no existe");
            }
            // Finalizamos la transacción y cerramos el EntityManager
            em.getTransaction().commit();
            em.close();

            System.out.println("Pulsa una tecla para continuar");
            scanner.nextLine();

        } catch (Exception e) {
            System.out.println("Error al modificar empresa");
            e.getMessage();
            e.printStackTrace();
        }
    }
}