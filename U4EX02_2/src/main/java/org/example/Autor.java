package org.example;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "autor", schema = "biblioteca")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "dataNaixement")
    private LocalDate dataNaixement;

    @OneToMany(mappedBy = "autor")
    private Set<org.example.Llibre> llibres = new LinkedHashSet<>();

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

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(LocalDate dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public Set<org.example.Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<org.example.Llibre> llibres) {
        this.llibres = llibres;
    }

}