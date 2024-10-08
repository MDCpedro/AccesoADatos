package u1ex01;
import java.io.FileWriter;
import java.io.IOException;

public class AccesFileWriter43235705y {
    public void EscribirArchivo(String path1, String texto) {

        try {
            FileWriter escritor = new FileWriter(path1, false);
            escritor.write(texto);
            escritor.close();
            System.out.println("Archivo escrito correctamente.");
            
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo o permiso denegado.");
        }
    }
}