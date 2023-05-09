package com.example.demo.controller;

import com.example.demo.models.Curso;
import com.example.demo.models.PerfilDTO;
import com.example.demo.service.CursosService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("curso")
public class CursosController
{

    @Autowired
    private CursosService cs;

    @GetMapping("consultarCurso/{id}")
    public ResponseEntity consultarCursos(@PathVariable final @NotNull Integer id)
    {
        return ResponseEntity.ok(cs.getCurso(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Curso>> getAll() {
        try {
            List<Curso> cursos = cs.getAll();
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping
    @RequestMapping(value = "addCurso",method = RequestMethod.POST)
    public Curso addCurso (@RequestBody Curso c)
    {
        return cs.addCurso(c).getBody();
    }

    @PostMapping("/{id}/updateCurso")
    public ResponseEntity<?> updateCursos(@PathVariable final @NotNull Integer id, @RequestBody final Curso c)
    {
        return cs.updateCurso(id, c);
    }

    @PostMapping("/{id}/deleteCurso")
    public ResponseEntity<?> deleteCursos(@PathVariable final @NotNull Integer id)
    {
        return cs.deleteCurso(id);
    }

    @GetMapping("/{id}/perfilList")
    public List<PerfilDTO> getCursoPerfil(@PathVariable Integer id)
    {
        return cs.getCursoPerfil(id);
    }

}
