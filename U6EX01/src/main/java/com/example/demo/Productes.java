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

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


@RestController
@RequestMapping("/get")
public class Productes {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/productes") 

    public ResponseEntity<List<Map<String, Object>>> getProductes() {
        try {
            String sql = "SELECT * FROM productes";
            List<Map<String, Object>> listaProductos = jdbcTemplate.queryForList(sql);
            return new ResponseEntity<>(listaProductos, HttpStatus.OK);
        
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
            return null;
        }
    }

    @GetMapping("/productes/{id}")
    public ResponseEntity<Map<String, Object>> getProductesID(@PathVariable int id) {
        try {
            String sql = "SELECT * FROM productes WHERE id = ?";
            Map<String, Object> producto = jdbcTemplate.queryForMap(sql, id);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}