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
    public ResponseEntity addPerfil (@RequestBody final @NotNull Perfil p)
    {
        return ps.add();
    }

    @PostMapping(/{id}/update)
    public ResponseEntity updatePerfil(PathVariable final @NotNull Integer id, @RequestBody final @NotNull Perfil p)
    {
        return ps.update(id, p);
    }

}
