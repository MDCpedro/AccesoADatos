package u1ex02b;

import org.xml.sax.helpers.DefaultHandler;
import java.io.FileWriter;

public class SAXHandler extends DefaultHandler {
    // creamos unos booleanos que nos serviran para confirmar el elemento que estamos
    boolean isLibro = false;
    boolean isAutor = false;
    boolean isTitulo = false;
    boolean isAño = false;
    boolean isResumen = false;
    // creamos el objeto libro, para que sea visto por los demas metodos
    ArticuloLibro libro;
    // creamos un StringBuilder para almacenar el contenido de cada tag
    StringBuilder contenidoActual = new StringBuilder();

    // este método se ejecuta cuando se encuentra un elemento, si es el tag que
    // buscamos, creamos un objeto nuevo
    // y ponemos true en cada booleano.
    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) {
        if (qName.equalsIgnoreCase("llibre")) {
            libro = new ArticuloLibro(null, null, 0, null);
            isLibro = true;
            System.out.println("-------------------");
        } else if (qName.equalsIgnoreCase("Autor")) {
            isAutor = true;
        } else if (qName.equalsIgnoreCase("Titol")) {
            isTitulo = true;
        } else if (qName.equalsIgnoreCase("Any")) {
            isAño = true;
        } else if (qName.equalsIgnoreCase("Resum")) {
            isResumen = true;
        }
        contenidoActual.setLength(0);
    }

    // este método se ejecuta cuando se encuentra el contenido de cada tag

    @Override
    public void characters(char[] ch, int start, int lenght) {
        contenidoActual.append(new String(ch, start, lenght).trim());
    }

    // este método se ejecuta cuando se cierra un elemento, si el booleano es true,
    // añadimos el contenido al objeto
    // y lo mostramos por consola y ponemos el booleano a false para indicr que ya
    // se ha añadido el contenido. Usamos filewriter para escribir el contenido en el archivo.
    @Override
    public void endElement(String uri, String localName, String qName) {
        try {
            FileWriter escritor = new FileWriter("app\\src\\main\\resources\\Any" + libro.getAño() + ".txt", true);
            if (isAutor) {
                libro.setAutor(contenidoActual.toString());
                System.out.println("Autor: " + libro.getAutor());
                escritor.write("\n"+ contenidoActual.toString());
                isAutor = false;

            } else if (isTitulo) {
                libro.setTitulo(contenidoActual.toString());
                System.out.println("Titulo: " + libro.getTitulo());
                escritor.write("\n"+ contenidoActual.toString());
                isTitulo = false;

            } else if (isAño) {
                libro.setAño(Integer.parseInt(contenidoActual.toString()));
                System.out.println("Año: " + libro.getAño());
                escritor.write(String.valueOf("\n"+ contenidoActual.toString()));
                isAño = false;
                
            } else if (isResumen) {
                libro.setResumen(contenidoActual.toString());
                System.out.println("Resumen: " + libro.getResumen());
                escritor.write("\n"+ contenidoActual.toString());
                isResumen = false;
            }
            escritor.close();
        } catch (Exception e) {
            System.out.println("Error al crear el archivo");
        }
    }
}
