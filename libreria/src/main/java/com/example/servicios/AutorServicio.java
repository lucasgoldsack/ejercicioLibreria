package com.example.servicios;

import com.example.Error.ErrorServicio;
import com.example.entidades.Autor;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.repositorios.AutorRepositorio;

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Transactional(propagation = Propagation.NESTED)
    public void agregar(String nombre, Boolean alta) throws ErrorServicio{
        validar(nombre, nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(Boolean.TRUE);
        
        autorRepositorio.save(autor);    
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String nombre, String id) throws ErrorServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            
             autorRepositorio.save(autor);
        }else{
             throw new ErrorServicio ("No hay un autor con ese id");
        }
        
        
        
    }
    
//    public void listarAutor(Autor autor){
//        
//        autorRepositorio.findById(autor.getId());
//        
//    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(Boolean.FALSE);
            
             autorRepositorio.save(autor);
        }else{
           throw new ErrorServicio ("No hay un autor con ese id");  
        }
        
    }
    
    public void consultar(String id) throws ErrorServicio{
         Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
        }else{
            throw new ErrorServicio ("No hay un autor con ese id");
        }
            
            
        
        
    }
    
    public void validar(String nombre, String id) throws ErrorServicio{
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("el autor no puede ser nulo");    
        }
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("el autor debe de tener un id");    
        }
        
    }
    
    
}
