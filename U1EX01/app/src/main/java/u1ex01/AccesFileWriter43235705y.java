package u1ex01;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AccesFileWriter43235705y {
    public void EscribirArchivo(String nombreArchivo, String texto) {

        try {
            FileWriter escritor = new FileWriter("app\\src\\main\\resources\\"+nombreArchivo+".txt", false);
            escritor.write(texto);
            escritor.close();
            System.out.println("Archivo escrito correctamente.");
            
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
        }

    }
}