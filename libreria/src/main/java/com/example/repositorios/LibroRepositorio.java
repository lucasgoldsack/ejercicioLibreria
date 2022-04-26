package com.example.repositorios;

import com.example.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
    
    @Query("SELECT c FROM Libro WHERE ")
    public Libro buscarLibro(@Param("libro") String libro);
    
}
