package u1ex01;
import java.io.File;
import u1ex01.AccesFileReader43235705y;
import u1ex01.AccesFile43235705y;
public class App {

    public static void main(String[] args) {

        AccesFileWriter43235705y escritor = new AccesFileWriter43235705y();
        escritor.EscribirArchivo("Buenas", "Hola a secas");

        AccesFileReader43235705y lector = new AccesFileReader43235705y();
        lector.LeerArchivo("/Test.txt");

        AccesFile43235705y crear = new AccesFile43235705y();
        crear.CrearArchivo("Prueba");


    }
}
