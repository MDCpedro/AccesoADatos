package com.example.demo;

public class Producto {
    private Integer id;
    private String nom;
    private String descripcio;
    private Double preu;
    private Integer quantitat;

    public Producto(Integer id, String nom, String descripcio, Double preu, Integer quantitat) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.preu = preu;
        this.quantitat = quantitat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Double getPreu() {
        return preu;
    }

    public void setPreu(Double preu) {
        this.preu = preu;
    }

    public Integer getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(Integer quantitat) {
        this.quantitat = quantitat;
    }
}
