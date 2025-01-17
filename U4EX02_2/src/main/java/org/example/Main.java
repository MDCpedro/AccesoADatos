package org.example;


import org.hibernate.Session;

import java.time.LocalDate;

public class Main
{
    public static void main( String[] args ) {
        Session session = Hibernate.getSession();
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
