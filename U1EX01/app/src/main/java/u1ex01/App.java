package u1ex01;
import java.io.File;
import u1ex01.AccesFileReader43235705y;
import u1ex01.AccesFileWriter43235705y;
import u1ex01.AccesInputStream43235705y;
import u1ex01.AccesOutputStream43235705y;
import u1ex01.AccesFile43235705y;
public class App {

    public static void main(String[] args) {

        String pathtOrigen = "app\\src\\main\\resources\\ArchivoOrigen.jpg";
        String pathtxt2 = "app\\src\\main\\resources\\ArchivoDestino.txt";
        String pathjpg2 = "app\\src\\main\\resources\\ArchivoDestino.jpg";

       
        AccesFileWriter43235705y escritor = new AccesFileWriter43235705y();
        AccesFileReader43235705y lector = new AccesFileReader43235705y();
        AccesInputStream43235705y lectorBinario = new AccesInputStream43235705y();
        AccesOutputStream43235705y escritorBinario = new AccesOutputStream43235705y();
        AccesFile43235705y permisos = new AccesFile43235705y();

        if (pathtOrigen.endsWith(".txt")) {
            escritor.EscribirArchivo(pathtxt2, lector.LeerArchivo(pathtOrigen));
        } else {
            escritorBinario.EscribirArchivo(pathjpg2, lectorBinario.LeerArchivo(pathtOrigen));
        }
        
    
        permisos.QuitarEscritura(pathtOrigen);
        escritor.EscribirArchivo(pathtOrigen, "Hola probando permisos jeje");
        
    }
}
