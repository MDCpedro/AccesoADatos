package com.example;

import java.io.File;
import java.io.FileReader;

import javax.naming.Context;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;



public class Main {
    public static void main(String[] args) {
        try {
            
        JAXBContext context = JAXBContext.newInstance(ArticuloLibro.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ArticuloLibro libro = (ArticuloLibro) unmarshaller.unmarshal(new File("u1ex02c\\src\\main\\resources\\llibres.xml"));
        System.out.println(libro.getTitulo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}