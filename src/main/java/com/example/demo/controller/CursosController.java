package com.example.demo.controller;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.models.Curso;
import com.example.demo.models.PerfilDTO;
import com.example.demo.service.CursosService;
import com.example.demo.tools.ValidateService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("curso")
@CrossOrigin(origins = "*")
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

    @PostMapping("/addCurso")
    public ResponseEntity<Object> addCurso(@RequestBody Map<String, String> request) {
        Curso curso = new Curso();
        curso.setNombre(request.get("nombre"));
        curso.setDuracion(Integer.parseInt(request.get("duracion")));
        List<String> errors = ValidateService.validateUser(curso);
        try {
            // Validar el objeto de entrada
            if (!errors.isEmpty()) {
                String errorMessage = String.join("\n", errors);
                throw new BadRequestException(errorMessage);
            }

            cs.addCurso(curso);
            return ResponseEntity.status(OK).body("Curso registrado");

        }catch (BadRequestException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(BAD_REQUEST).body("Hubo un error al cargar el usuario");
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
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
