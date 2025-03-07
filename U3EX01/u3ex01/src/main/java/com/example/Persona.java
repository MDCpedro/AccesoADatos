package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.objectdb.o.CLN.p;
// usamos la anotación @Entity para indicar que esta clase es una entidad de la base de datos
@Entity
public class Persona {
    // usamos la anotación @Id para indicar que este campo es la clave primaria
    // usamos la anotación @GeneratedValue para indicar que el valor de este campo se genera automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private int edad;
    private String email;
    // este constructor vacío es necesario para que ObjectDB pueda instanciar la clase
    public Persona() {
    }
    // este constructor es el que usaremos para crear instancias de la clase Persona
    public Persona(String nombre, int edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }
    // getters y setters
    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String printPersona() {
        return "ID: " + id + " | Nombre: " + nombre + " | Edad: " + edad + " | Correo: " + email + " | ";
    }
}
