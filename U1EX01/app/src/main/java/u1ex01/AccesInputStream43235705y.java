package u1ex01;
import java.io.FileInputStream;

public class AccesInputStream43235705y {
    public String LeerArchivo(String path1) {
        try {
            StringBuilder contenido = new StringBuilder();
            FileInputStream lector = new FileInputStream(path1);
            
            int data;
            while ((data = lector.read()) != -1) {
                contenido.append((char) data);
            }
            lector.close();
            System.out.println(contenido.toString());

            return contenido.toString();

        } catch (Exception e) {
            System.out.println("Error al leer archivo binario o permiso denegado");
            return null;
        } 
        
    } 
}