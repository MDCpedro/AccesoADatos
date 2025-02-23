package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@RestController
// usamos @RequestMapping para indicar la ruta
@RequestMapping("/put")
public class PutProductes {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // usamos @PutMapping para indicar que es un PUT
    @PutMapping("/productes/{id}")
    // Este método recibe un JSON con los datos del producto y los modifica en la base de datos usando jdbcTemplate
    public ResponseEntity<String> modificarProducto(@RequestBody Map <String, Object> producto, @PathVariable int id) {
        try {
            // Comprobamos que el producto exista en la base de datos si no devolvemos un error
            if (jdbcTemplate.queryForList("SELECT * FROM productes WHERE id = ?", id).size() == 0) {
                return new ResponseEntity<>("Error al actualizar el producto (Producto no encontrado)", HttpStatus.NOT_FOUND);
            } else {
                // Obtenemos los datos del del JSON y los guardamos en variables
                String nom = (String) producto.get("nom");
                String descripcio = (String) producto.get("descripcio");
                Double preu = Double.parseDouble(producto.get("preu").toString());
                int quantitat = Integer.parseInt(producto.get("quantitat").toString()); 
                // Comprobamos que los campos no sean nulos si lo son devolvemos un error
                if (nom == null || descripcio == null || preu == null || quantitat == 0) {
                    return new ResponseEntity<>("Error al actualizar el producto (Faltan campos)", HttpStatus.BAD_REQUEST);
                } else {
                    // Si no actualizamos los datos en la base de datos
                    jdbcTemplate.update("UPDATE productes SET nom = ?, descripcio = ?, preu = ?, quantitat = ? WHERE id = ?", nom, descripcio, preu, quantitat, id);
                    System.out.println("Producto modificado");
                    return new ResponseEntity<>("Producto modificado", HttpStatus.OK);
                }
            }
        // Si hay algún error devolvemos un error de servidor
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error al actualizar el producto Fallo de servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
