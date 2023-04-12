package com.example.demo.controller;

import com.example.demo.models.Perfil;
import com.example.demo.service.PerfilService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("perfil")
public class PerfilController
{

    @Autowired
    private PerfilService ps;

    @GetMapping("consultarPerfil/{id}")
    public ResponseEntity consultarPerfiles(@PathVariable final @NotNull Integer id)
    {
        return ResponseEntity.ok(ps.getPerfil(id));
    }

    @PostMapping
    @RequestMapping(value = "addPerfil",method = RequestMethod.POST)
    public Perfil addPerfil (@RequestBody Perfil p)
    {
        return ps.addPerfil(p).getBody();
        //return ResponseEntity.status(HttpStatus.CREATED).body(ps.addPerfil(p));
    }

    @PostMapping("/{id}/updatePerfil")
    public ResponseEntity<?> updatePerfiles(@PathVariable final @NotNull Integer id, @RequestBody final Perfil p)
    {
        return ps.updatePerfil(id, p);
    }

}
