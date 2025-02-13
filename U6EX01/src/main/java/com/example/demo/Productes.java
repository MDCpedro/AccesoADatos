package com.example.demo;

import java.time.format.DateTimeFormatter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Statement;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/get")
public class Productes {
    @GetMapping("/productes") 
    public void getProductes() {
        String url = "jdbc:sqlite:src/main/resources/db/productes.db";
        String sql = "SELECT * FROM productes";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("nom") + "\t" +
                                   rs.getString("descripcio") + "\t" +
                                   rs.getDouble("preu") + "\t" +
                                   rs.getInt("stock") + "\t" +
                                   rs.getString("data"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
           
        }
    }
}