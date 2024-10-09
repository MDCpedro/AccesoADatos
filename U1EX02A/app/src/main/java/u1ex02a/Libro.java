package u1ex02a;

public class Libro {
    private String titol;
    private String autor;
    private String any;
    private String resumen;

    public Libro(String titol, String autor, String any, String resumen) {
        this.titol = titol;
        this.autor = autor;
        this.any = any;
        this.resumen = resumen;
    }

    public String getTitol() {
        return titol;
    }
    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAny() {
        return any;
    }
    public void setAny(String any) {
        this.any = any;
    }

    public String getResumen() {
        return resumen;
    }
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
}
