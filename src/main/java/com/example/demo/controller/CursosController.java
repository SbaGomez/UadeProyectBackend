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
    public ResponseEntity ConsultarCursos(@PathVariable final @NotNull Integer id)
    {
        return ResponseEntity.ok(cs.getCurso(id));
    }

    @PostMapping
    @RequestMapping(value = "addCurso",method = RequestMethod.POST)
    public Curso addCurso (@RequestBody Curso c)
    {
        return cs.addCurso(c).getBody();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<?> updateCurso(@PathVariable final @NotNull Integer id, @RequestBody final Curso c)
    {
        return cs.updateCurso(id, c);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteCurso(@PathVariable final @NotNull Integer id)
    {
        return cs.deleteCurso(id);
    }

}
