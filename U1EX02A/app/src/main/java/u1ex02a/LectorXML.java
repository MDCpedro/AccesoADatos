package u1ex02a;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.FileWriter;


public class LectorXML {
    public void leerXML() {
        // indico el archivo a leer
            File archivo = new File("app\\src\\main\\resources\\llibres.xml");

            try {
            // creamos las clases del DOM necesarias

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(archivo);

            //  normalizo el documento para que no haya espacios

                doc.getDocumentElement().normalize();

            // esta lista guardara cada libro

                NodeList listaLibros = doc.getElementsByTagName("llibre");
                
            // hacemos un contador que recorre la lista que hemos creado, luego hacemoos un node que seria el libro seleccionado 

                for (int i = 0; i < listaLibros.getLength(); i++) {
                    Node libroActual = listaLibros.item(i);

            // si el nodo es un elemento, lo guardamos en campo y mostramos los datos

                    if (libroActual.getNodeType() == libroActual.ELEMENT_NODE) {

                        Element campo = (Element) libroActual;
                
            // creamos un objeto libro con los datos del campo

                    Libro libro = new Libro(campo.getElementsByTagName("titol").item(0).getTextContent(),
                    campo.getElementsByTagName("autor").item(0).getTextContent(), 
                    campo.getElementsByTagName("any").item(0).getTextContent(), 
                    campo.getElementsByTagName("resum").item(0).getTextContent());

            // detectamos el autor y guardamos los datos en un archivo de texto con su nombre
                  
                    FileWriter escritor = new FileWriter("app\\src\\main\\resources\\" + libro.getAutor() + ".txt", true);
                    escritor.write("Titulo: "+libro.getTitol()+"\n");
                    escritor.write("Autor: "+libro.getAutor()+"\n");
                    escritor.write("Año: "+libro.getAny()+"\n");
                    escritor.write("Resumen: "+libro.getResumen()+"\n");
                    escritor.write("\n");
                    escritor.close();
                        
            // hacemos un sout de cada campo
                    System.out.println(i);
                    System.out.println("Titulo: "+libro.getTitol());
                    System.out.println("Autor: "+libro.getAutor());
                    System.out.println("Año: "+libro.getAny());
                    System.out.println("Resumen: "+libro.getResumen());
                    System.out.println("");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            
    }
}