package u1ex02b;
import org.checkerframework.checker.units.qual.A;
import org.xml.sax.helpers.DefaultHandler;
import u1ex02b.ArticuloLibro;
import java.io.FileWriter;
//usamos DefaultHandler para no tener que implementar todos los métodos de la interfaz ContentHandler
public class SAXHandler extends DefaultHandler {
//creamos unos booleanos que nos serviran para confirmar el elemento que estamos
    boolean isAutor = false;
    boolean isTitulo = false;
    boolean isAño = false;
    boolean isResumen = false;
//creamos el objeto libro, para que sea visto por los demas metodos
    ArticuloLibro libro = new ArticuloLibro(null, null, 0, null);
//este método se ejecuta cuando se encuentra un elemento, si es el tag que buscamos, creamos un objeto nuevo
//y ponemos true en cada booleano.
    @Override   
    public void startElement (String uri, String localName, String qName, org.xml.sax.Attributes attributes) {
        libro = new ArticuloLibro(null, null, 0, null);
        if (qName.equalsIgnoreCase("Autor")) {
            isAutor = true;
        } else if (qName.equalsIgnoreCase("Titol")) {
            isTitulo = true;
        } else if (qName.equalsIgnoreCase("Any")) {
            isAño = true;
        } else if (qName.equalsIgnoreCase("Resum")) {
            isResumen = true;
        }
    }  

//este método se ejecuta cuando se encuentra el contenido de cada tag
//, si el booleano es true, añadimos el valor al objeto y un sout con el contenido.
//ponemos el booleano a false para indicar que ya hemos mostrado el contenido.
    @Override
    public void characters(char[] ch, int start, int lenght) {
        
        if (isAutor) {
            libro.setAutor(new String(ch, start, lenght));
            System.out.println("Autor: " + libro.getAutor());
            isAutor = false;
        } else if (isTitulo) {
            libro.setTitulo(new String(ch, start, lenght));
            System.out.println("Titulo: " + libro.getTitulo());
            isTitulo = false;
        } else if (isAño) {
            libro.setAño(Integer.parseInt(new String(ch, start, lenght)));
            System.out.println("Año: " + libro.getAño());
            isAño = false;
        } else if (isResumen) {
            libro.setResumen(new String(ch, start, lenght));
            System.out.println("Resumen: " + libro.getResumen());
            isResumen = false;
        }

    }
    //este método se ejecuta cuando se cierra un elemento, si el tag es llibre, hacemos un sout para separar cada libro.
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("llibre")) {
            System.out.println("-------------------");
        }
    //usamos Filewriter para escribir en cada 
        try {
            FileWriter escritor = new FileWriter("app\\src\\main\\resources\\Any" + libro.getAño() + ".txt", true);
            escritor.write(libro.getAutor() + "\n");
            escritor.write(libro.getTitulo() + "\n");
            escritor.write(String.valueOf(libro.getAño()) + "\n");
            escritor.write(libro.getResumen() + "\n");
            escritor.write("-------------------\n");
            escritor.close();
        } catch(Exception e) {
            System.out.println("Error al crear el archivo");
        }
    }
}
