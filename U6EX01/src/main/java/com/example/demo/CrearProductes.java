package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@RestController
@RequestMapping("/post/productes")
public class CrearProductes {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostMapping()
    public ResponseEntity<String> crearProducto(@RequestBody Map <String, Object> producto) {
        // String url = "jdbc:sqlite:src/main/resources/basedatos.sqlite";
        try {
            String nom = (String) producto.get("nom");
            String descripcio = (String) producto.get("descripcio");
            Double preu = Double.parseDouble(producto.get("preu").toString());
            int quantitat = Integer.parseInt(producto.get("quantitat").toString()); 
            jdbcTemplate.update("INSERT INTO productes (nom, descripcio, preu, quantitat) VALUES (?, ?, ?, ?)", nom, descripcio, preu, quantitat);
            System.out.println("Producto creado");

            return new ResponseEntity<>("Producto creado", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
