package com.example.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class PortalControlador {

    @GetMapping("")
    public String index() {

        return "AgregarAutor";
    } 
    
     @GetMapping("/autores")
    public String index2() {

        return "autor.html";
    } 
    
    @GetMapping("/3")
    public String index3() {

        return "index.html";
    } 
    
//    @GetMapping("/AgregarAutor")
//    public String agregarAutor() {
//
//        return "AgregarAutor";
//    }
//    
//    @PostMapping("/Agregar")
//    public String agregar(@RequestParam String nombreAutor){
//        System.out.println("se cargó");
//        
//        return "AgregarAutor.html";
//    }
    
    
}

