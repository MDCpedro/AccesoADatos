package u1ex02b;

public class ArticuloLibro {
    private String Autor;
    private String Titulo;
    private int año;
    private String resumen;

    public ArticuloLibro(String Autor, String Titulo, int año, String resumen) {
        this.Autor = Autor;
        this.Titulo = Titulo;
        this.año = año;
        this.resumen = resumen;
    }

    public String getAutor() {
        return Autor;
    }
    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public int getAño() {
        return año;
    }
    public void setAño(int año) {
        this.año = año;
    }

    public String getResumen() {
        return resumen;
    }
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

}
