package com.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"autor", "titol", "any", "resum"})
public class ArticuloLibro {
    private String Autor;
    private String Titol;
    private int any;
    private String resum;

    public ArticuloLibro() {
    }   

    public ArticuloLibro(String Autor, String Titol, int any, String resum) {
        this.Autor = Autor;
        this.Titol = Titol;
        this.any = any;
        this.resum = resum;
    }
    
    @XmlElement(name = "autor")
    public String getAutor() {
        return Autor;
    }
    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    @XmlElement(name = "titol")
    public String getTitol() {
        return Titol;
    }
    public void setTitol(String Titol) {
        this.Titol = Titol;
    }

    @XmlElement(name = "any")
    public int getany() {
        return any;
    }
    public void setany(int any) {
        this.any = any;
    }

    @XmlElement(name = "resum")
    public String getresum() {
        return resum;
    }
    public void setresum(String resum) {
        this.resum = resum;
    }

}
