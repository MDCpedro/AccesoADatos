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
// usamos @RequestMapping para indicar la ruta
@RequestMapping("/post/productes")
public class PostProductes {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // usamos @PostMapping para indicar que es un POST
    @PostMapping()
    // Este método recibe un JSON con los datos del producto y los inserta en la base de datos usando jdbcTemplate
    public ResponseEntity<String> crearProducto(@RequestBody Map <String, Object> producto) {
        try {
            // Obtenemos los datos del del JSON y los guardamos en variables
            String nom = (String) producto.get("nom");
            String descripcio = (String) producto.get("descripcio");
            Double preu = Double.parseDouble(producto.get("preu").toString());
            Integer quantitat = (Integer) producto.get("quantitat"); 
            // Comprobamos que los campos no sean nulos si lo son devolvemos un error
            if (nom == null || descripcio == null || preu == null || quantitat == null) {
                return new ResponseEntity<>("Error al crear el producto (Faltan campos)", HttpStatus.BAD_REQUEST);
            }
            // Insertamos los datos en la base de datos
            jdbcTemplate.update("INSERT INTO productes (nom, descripcio, preu, quantitat) VALUES (?, ?, ?, ?)", nom, descripcio, preu, quantitat);
            System.out.println("Producto creado");
            // Devolvemos un mensaje de éxito
            return new ResponseEntity<>("Producto creado", HttpStatus.OK);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error al crear el producto (Fallo de servidor)", HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}
