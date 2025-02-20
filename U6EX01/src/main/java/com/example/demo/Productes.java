package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;


@RestController
@RequestMapping("/get")
public class Productes {

    @GetMapping("/productes") 

    public List<Producto> getProductes() {
        String url = "jdbc:sqlite:src/main/resources/basedatos.sqlite";
        String sql = "SELECT * FROM productes";
        List<Producto> listaProductos = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("id"), 
                rs.getString("nom"), 
                rs.getString("descripcio"), 
                rs.getDouble("preu"), 
                rs.getInt("quantitat"));
                listaProductos.add(producto);
            }
            return listaProductos;

        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
            return null;
        }
    }
}