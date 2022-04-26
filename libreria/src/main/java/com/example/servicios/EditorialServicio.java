package com.example.servicios;

import com.example.Error.ErrorServicio;
import com.example.entidades.Editorial;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.repositorios.EditorialRepositorio;

public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional(propagation = Propagation.NESTED)
    public void agregar(String nombre, Boolean alta) throws ErrorServicio{
        validar(nombre, nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(Boolean.TRUE);
        
        editorialRepositorio.save(editorial);    
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String nombre, String id) throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            
             editorialRepositorio.save(editorial);
        }else{
             throw new ErrorServicio ("No hay un editorial con ese id");
        }
        
        
        
    }
    
//    public void listarAutor(Autor autor){
//        
//        autorRepositorio.findById(autor.getId());
//        
//    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(Boolean.FALSE);
            
             editorialRepositorio.save(editorial);
        }else{
           throw new ErrorServicio ("No hay un editorial con ese id");  
        }
        
    }
    
    public void consultar(String id) throws ErrorServicio{
         Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
        }else{
            throw new ErrorServicio ("No hay un editorial con ese id");
        }
            
            
        
        
    }
    
    public void validar(String nombre, String id) throws ErrorServicio{
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("el editorial no puede ser nulo");    
        }
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("el editorial debe de tener un id");    
        }
        
    }
    
}
