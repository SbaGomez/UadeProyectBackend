package com.example.demo.tools;

import com.example.demo.models.Curso;
import com.example.demo.models.Perfil;

import java.util.ArrayList;
import java.util.List;

public class ValidateService {

    public static List<String> validateCurso(Curso c) {
        List<String> errors = new ArrayList<>();
        if (c.getNombre() == null || c.getNombre().isEmpty()) {
            errors.add("El nombre es requerido");
        }

        if (c.getDuracion() == 0) {
            errors.add("La duracion es requerida");
        }
        return errors;
    }

    public static List<String> validatePerfil(Perfil p) {
        List<String> errors = new ArrayList<>();
        if (p.getNombre() == null || p.getNombre().isEmpty()) {
            errors.add("El nombre es requerido");
        }

        if (p.getApellido() == null || p.getApellido().isEmpty()) {
            errors.add("El apellido es requerida");
        }
        return errors;
    }
}
