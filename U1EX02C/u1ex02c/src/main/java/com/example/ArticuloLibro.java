package com.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "llibre")
public class ArticuloLibro {
    private String Autor;
    private String Titulo;
    private int año;
    private String resumen;

    public ArticuloLibro() {
    }   

    public ArticuloLibro(String Autor, String Titulo, int año, String resumen) {
        this.Autor = Autor;
        this.Titulo = Titulo;
        this.año = año;
        this.resumen = resumen;
    }
    
    @XmlElement(name = "autor")
    public String getAutor() {
        return Autor;
    }
    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    @XmlElement(name = "titol")
    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    @XmlElement(name = "any")
    public int getAño() {
        return año;
    }
    public void setAño(int año) {
        this.año = año;
    }

    @XmlElement(name = "resum")
    public String getResumen() {
        return resumen;
    }
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

}
