package com.example;

import java.io.File;
import java.io.FileWriter;
import java.util.Comparator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.util.List;


public class Main {
    public static void main(String[] args) {
            try {

                JAXBContext context = JAXBContext.newInstance(Listalibros.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Listalibros libros = (Listalibros) unmarshaller.unmarshal(new File("u1ex02c\\src\\main\\resources\\llibres.xml"));
                List<ArticuloLibro> listalibros = libros.getLibros();
                FileWriter escritor = new FileWriter("u1ex02c\\src\\main\\resources\\LibrosOrdenCronologico.txt", true);

//imprimimos por consola el contenido por como llega.
                for (ArticuloLibro llibre : listalibros) {
                    System.out.println("Autor: " + llibre.getAutor());
                    System.out.println("Título: " + llibre.getTitol());
                    System.out.println("Año: " + llibre.getany()); 
                    System.out.println("Resumen: " + llibre.getresum());
                    System.out.println("--------------------------");
                }
//escribimos en el archivo por orden cronologico
                listalibros.sort(Comparator.comparingInt(ArticuloLibro::getany));
                for (ArticuloLibro llibre : listalibros) {
                    escritor.write("Autor: " + llibre.getAutor() + "\n");
                    escritor.write("Titulo: " + llibre.getTitol() + "\n");
                    escritor.write("Fecha: " + llibre.getany() + "\n");
                    escritor.write("Resumen: " + llibre.getresum() + "\n");
                    escritor.write("--------------------------\n");
                }
                escritor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}