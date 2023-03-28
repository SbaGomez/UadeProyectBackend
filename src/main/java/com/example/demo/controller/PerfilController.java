package com.example.demo.controller;

import com.example.demo.model.Perfil;
import com.example.demo.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController
{

    @Autowired
    private PerfilService ps;

    @PostMapping("")
    public ResponseEntity addPerfil (@RequestBody final Perfil p)
    {
        return ps.addPerfil(p);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity updatePerfil(@PathVariable final Integer id, @RequestBody final Perfil p)
    {
        return ps.updatePerfil(id, p);
    }

}
