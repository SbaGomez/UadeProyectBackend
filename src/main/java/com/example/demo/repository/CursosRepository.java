package com.example.demo.repository;

import com.example.demo.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursosRepository extends JpaRepository<Curso, Integer>
{
    @Query(value = "select perfil_id from perfilesxcursos where curso_id = :id", nativeQuery = true)
    List<Integer> findCursoPerfil(Integer id);
}
