package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;


@RestController
// usamos @RequestMapping para indicar la ruta
@RequestMapping("/get")
public class GetProductes {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // usamos @GetMapping para indicar que es un GET
    @GetMapping("/productes") 
    // Este método devuelve una lista con todos los productos de la base de datos
    public ResponseEntity<List<Map<String, Object>>> getProductes() {
        try {
            // Seleccionamos todo en la base de datos
            String sql = "SELECT * FROM productes";
            // Si no hay productos devolvemos un error
            if  (jdbcTemplate.queryForList(sql).size() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                // Si hay productos los devolvemos
                List<Map<String, Object>> listaProductos = jdbcTemplate.queryForList(sql);
                return new ResponseEntity<>(listaProductos, HttpStatus.OK);
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
            return null;
        }
    }
    // Usamos @GetMapping para indicar que es un GET y le pasamos el id del producto
    @GetMapping("/productes/{id}")
    // Este método devuelve un producto de la base de datos con el id que le pasamos por parámetro
    public ResponseEntity<Map<String, Object>> getProductesID(@PathVariable int id) {
        // Seleccionamos el producto con el id que le pasamos y lo devolvemos
        try {
            String sql = "SELECT * FROM productes WHERE id = ?";
            Map<String, Object> producto = jdbcTemplate.queryForMap(sql, id);
            return new ResponseEntity<>(producto, HttpStatus.OK);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}