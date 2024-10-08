package u1ex01;
import java.io.FileOutputStream;

public class AccesOutputStream43235705y {
    public void EscribirArchivo(String path1, String texto) {
        try {
            FileOutputStream escritor = new FileOutputStream(path1, false);
            escritor.write(texto.getBytes());
            escritor.close();
            System.out.println("Archivo binario escrito correctamente.");
            
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo binario o permiso denegado.");
        }
    }
}
