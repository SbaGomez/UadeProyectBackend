package com.example.demo.controller;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.models.Curso;
import com.example.demo.models.Perfil;
import com.example.demo.service.PerfilService;
import com.example.demo.tools.ValidateService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("perfil")
@CrossOrigin(origins = "*")
public class PerfilController
{

    @Autowired
    private PerfilService ps;

    @GetMapping("getAll")
    public ResponseEntity<List<Perfil>> getAll() {
        try {
            List<Perfil> perfiles = ps.getAll();
            return ResponseEntity.ok(perfiles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("consultarPerfil/{id}")
    public ResponseEntity<?> consultarPerfiles(@PathVariable final @NotNull Integer id)
    {
        return ResponseEntity.ok(ps.getPerfil(id));
    }

    @PostMapping("/{id}/deletePerfil")
    public ResponseEntity<?> deletePerfil(@PathVariable final @NotNull Integer id)
    {
        return ps.deleteCurso(id);
    }

    @PostMapping("/addPerfil")
    public ResponseEntity<Object> addPerfil(@RequestBody Map<String, String> request) {
        Perfil perfil = new Perfil();
        perfil.setNombre(request.get("nombre"));
        perfil.setApellido(request.get("apellido"));
        perfil.setDni(request.get("dni"));
        perfil.setSexo(request.get("sexo"));
        perfil.setEdad(Integer.parseInt(request.get("edad")));
        List<String> errors = ValidateService.validatePerfil(perfil);
        try {
            // Validar el objeto de entrada
            if (!errors.isEmpty()) {
                String errorMessage = String.join("\n", errors);
                throw new BadRequestException(errorMessage);
            }

            ps.addPerfil(perfil);
            return ResponseEntity.status(OK).body("Perfil registrado");

        }catch (BadRequestException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(BAD_REQUEST).body("Hubo un error al cargar el perfil");
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PostMapping("/{id}/updatePerfil")
    public ResponseEntity<?> updatePerfiles(@PathVariable final @NotNull Integer id, @RequestBody final Perfil p)
    {
        return ps.updatePerfil(id, p);
    }

}
