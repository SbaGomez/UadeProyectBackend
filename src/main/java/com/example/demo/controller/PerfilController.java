package com.example.demo.controller;

import com.example.demo.model.Perfil;
import com.example.demo.service.PerfilService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("perfil")
public class PerfilController
{

    @Autowired
    private PerfilService ps;

    /*@GetMapping
    @RequestMapping(value = "ConsultarPersonas",method = RequestMethod.GET)
    public ResponseEntity<?> ConsultarPersonas()
    {
        List<Persona> listaPersona=this.personaServiceImpl.ConsultarPersona();
        return ResponseEntity.ok(listaPersona);
    }*/

    @PostMapping
    @RequestMapping(value = "addPerfil",method = RequestMethod.POST)
    public ResponseEntity<?> addPerfil (@RequestBody Perfil p)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(ps.addPerfil(p));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<?> updatePerfil(@PathVariable final @NotNull Integer id, @RequestBody final Perfil p)
    {
        return ps.updatePerfil(id, p);
    }

}
