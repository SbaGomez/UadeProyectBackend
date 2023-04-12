package com.example.demo.controller;

import com.example.demo.models.Curso;
import com.example.demo.service.CursosService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity deleteCursos(@PathVariable final @NotNull Integer id)
    {
        return cs.deleteCurso(id);
    }

}
