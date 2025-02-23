package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;

@RestController
// usamos @RequestMapping para indicar la ruta
@RequestMapping("/delete")
public class DeleteProductes {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // usamos @DeleteMapping para indicar que es un DELETE
    @DeleteMapping("/productes/{id}")
    // Este método elimina un producto de la base de datos con el id que le pasamos por parámetro
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        try {
            // Comprobamos que el producto exista en la base de datos, si no devolvemos un error
            if (jdbcTemplate.update("DELETE FROM productes WHERE id = ?", id ) == 0) {
                return new ResponseEntity<>("Error al eliminar el producto (Producto no encontrado)", HttpStatus.NOT_FOUND);
            }
            // Si existe eliminamos el producto
            jdbcTemplate.update("DELETE FROM productes WHERE id = ?", id);
            System.out.println("Producto eliminado");
            // Devolvemos un mensaje de éxito
            return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error al eliminar el producto Fallo de servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
