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

            // detectamos si el autor es Stephen King o Richard Bach y guardamos los datos en un archivo de texto con su nombre

                        if (campo.getElementsByTagName("autor").item(0).getTextContent().equals("Stephen King")) {
                            FileWriter escritor = new FileWriter("app\\src\\main\\resources\\Stephen King.txt", true);
    
                            escritor.write("Titulo: " +campo.getElementsByTagName("titol").item(0).getTextContent()+ ".\n");
                            escritor.write("Autor: " +campo.getElementsByTagName("autor").item(0).getTextContent()+ ".\n");
                            escritor.write("Año: " +campo.getElementsByTagName("any").item(0).getTextContent()+ ".\n");
                            escritor.write("Resumen: " +campo.getElementsByTagName("resum").item(0).getTextContent()+ ".\n");
                            escritor.write("\n");
                            escritor.close();

                        } else {
                            FileWriter escritor = new FileWriter("app\\src\\main\\resources\\Richard Bach.txt", true);
    
                            escritor.write("Titulo: " +campo.getElementsByTagName("titol").item(0).getTextContent()+ ".\n");
                            escritor.write("Autor: " +campo.getElementsByTagName("autor").item(0).getTextContent()+ ".\n");
                            escritor.write("Año: " +campo.getElementsByTagName("any").item(0).getTextContent()+ ".\n");
                            escritor.write("Resumen: " +campo.getElementsByTagName("resum").item(0).getTextContent()+ ".\n");
                            escritor.write("\n");
                            escritor.close();

                        }
                        
            // hacemos un sout de cada campo
                    System.out.println(i);
                    System.out.println("Titulo: " +campo.getElementsByTagName("titol").item(0).getTextContent()+ ".");
                    System.out.println("Autor: " +campo.getElementsByTagName("autor").item(0).getTextContent()+ ".");
                    System.out.println("Año: " +campo.getElementsByTagName("any").item(0).getTextContent()+ ".");
                    System.out.println("Resumen: " +campo.getElementsByTagName("resum").item(0).getTextContent()+ ".");
                    System.out.println("");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            
    }
}