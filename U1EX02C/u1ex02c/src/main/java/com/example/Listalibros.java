package com.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "llibres")
public class Listalibros {
    private List<ArticuloLibro> libros;

    @XmlElement(name = "llibre")
    public List<ArticuloLibro> getLibros() {
        return libros;
    }

    public void setLibros(List<ArticuloLibro> libros) {
        this.libros = libros;
    }

}
