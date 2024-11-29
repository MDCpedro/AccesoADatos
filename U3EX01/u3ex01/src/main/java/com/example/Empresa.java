package com.example;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// usamos la anotación @Entity para indicar que esta clase es una entidad de la base de datos
@Entity
public class Empresa {
    // usamos la anotación @Id para indicar que este campo es la clave primaria
    // usamos la anotación @GeneratedValue para indicar que el valor de este campo se genera automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String cif;
    private String nombre;
    private String direccion;

    // este constructor vacío es necesario para que ObjectDB pueda instanciar la clase
    public Empresa() {
    }
    // este constructor es el que usaremos para crear instancias de la clase Empresa    
    public Empresa(String cif, String nombre, String direccion) {
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    // getters y setters
    public long getId() {
        return id;
    }

    public String getCif() {
        return cif;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String printEmpresa() {
        return "Id: " + id + " | CIF: " + cif + " | Nombre: " + nombre + " | Direccion: " + direccion + " | ";
    }
}
