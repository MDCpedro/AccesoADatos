package u1ex01;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class AccesFileReader43235705y {
    public void LeerArchivo(String nombre_archivo) {
        try {
            FileReader lector = new FileReader("app\\src\\main\\resources"+nombre_archivo+".txt");
            int caracter;
            while ((caracter = lector.read()) != -1) {
                System.out.print((char) caracter);
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al leer archivo.");
        }
    }
}