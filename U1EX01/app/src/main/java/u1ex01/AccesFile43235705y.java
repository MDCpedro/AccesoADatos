package u1ex01;
import java.io.File;

public class AccesFile43235705y {

    public void QuitarEscritura(String path1) {
        try {
            File archivo = new File(path1);
            archivo.setWritable(false);
            System.out.println("Permisos de escritura eliminados.");
            
        }
        catch (Exception e) {
            System.out.println("No se pudo quitar los permisos.");
        }
    }
}
