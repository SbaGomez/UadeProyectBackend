package com.example.demo.service;

import com.example.demo.models.Curso;
import com.example.demo.repository.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.*;

@Service
public class CursosService
{

    private final CursosRepository cursosRepository;

    @Autowired
    public CursosService(CursosRepository cursosRepository)
    {
        this.cursosRepository = cursosRepository;
    }

    public Curso addCurso(Curso curso)
    {
        try
        {
            cursosRepository.save(curso);
            return this.findById(curso.getId());

        }
        catch (Exception e)
        {
            //return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
            return null;
        }
    }

    public Curso findById(Integer id)
    {
        return cursosRepository.findAll().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(
                BAD_REQUEST, "Curso con identificador %d no encontrado"));
    }

    public Curso getCurso(Integer id)
    {
        return cursosRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Curso no encontrado"));
    }

    public ResponseEntity<?> deleteCurso(Integer id)
    {
        try
        {
            cursosRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }

    public ResponseEntity<?> updateCurso(Integer id, Curso curso)
    {
        try{
            Curso c = cursosRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Curso no encontrado"));
            c.setNombre(curso.getNombre());
            c.setDuracion(curso.getDuracion());
            cursosRepository.save(c);
            return ResponseEntity.status(OK).build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
