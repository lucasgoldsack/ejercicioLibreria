package com.example.servicios;

import com.example.Error.ErrorServicio;
import com.example.entidades.Autor;
import com.example.entidades.Editorial;
import com.example.entidades.Libro;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.repositorios.LibroRepositorio;

public class LibroServicio {
     @Autowired
    private LibroRepositorio libroRepositorio;
     
     @Transactional(propagation = Propagation.NESTED)

     public void agregar(Long isbn, String titulo, Boolean alta, Integer anio, Autor autor, Editorial editorial, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws ErrorServicio{
        validar(titulo, titulo, isbn, anio, autor, editorial);
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(libro.getEjemplares());
        libro.setAlta(Boolean.TRUE);
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        
        
        libroRepositorio.save(libro);    
    }
    
     @Transactional(propagation = Propagation.NESTED)
    public void modificar(Long isbn, String titulo, Integer anio, Autor autor, Editorial editorial, String id) throws ErrorServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            
            
             libroRepositorio.save(libro);
        }else{
             throw new ErrorServicio ("No hay un libro con ese id");
        }
        
        
        
    }
    
//    public void listarAutor(Autor autor){
//        
//        autorRepositorio.findById(autor.getId());
//        
//    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio{ //cambiar Id mas tarde, mu colicado para acceder despues y usarlo con html
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(Boolean.FALSE);
            
             libroRepositorio.save(libro);
        }else{
           throw new ErrorServicio ("No hay un libro con ese id");  
        }
        
    }
    
    public void consultar(String id) throws ErrorServicio{
         Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
        }else{
            throw new ErrorServicio ("No hay un libro con ese id");
        }
            
            
        
        
    }
    
    public void validar(String titulo, String id, Long isbn, Integer anio, Autor autor, Editorial editorial) throws ErrorServicio{
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El libro debe tener un nombre");
        }
        if (id == null) {
            throw new ErrorServicio(" El libro debe tener un Id");
        }
        if (isbn == null) {
            throw new ErrorServicio("El libro debe tener un Isbn");
        }
        if (anio == null) {
            throw new ErrorServicio("El libro debe tener un año");
        }
        if (autor == null) {
            throw new ErrorServicio("El libro debe tener un autor");
        }
        if (editorial == null) {
            throw new ErrorServicio("El libro debe tener una editorial");
        }
    }

        
    }
    
    

    

