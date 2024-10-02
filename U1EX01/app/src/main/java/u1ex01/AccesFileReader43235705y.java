package u1ex01;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.Permission;


public class AccesFileReader43235705y {
    public String LeerArchivo(String path1) {

        try {
            StringBuilder contenido = new StringBuilder();

            FileReader lector = new FileReader(path1);
            int caracter;
            while ((caracter = lector.read()) != -1) {
                contenido.append((char) caracter);
            }
            lector.close();
            System.out.println(contenido.toString());
            return contenido.toString();

        } catch (IOException e) {
            System.out.println("Error al leer archivo o permiso denegado.");
        } 
        return LeerArchivo(path1);
    }
}