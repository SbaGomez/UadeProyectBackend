package com.example.demo.tools;

import com.example.demo.models.Curso;

import java.util.ArrayList;
import java.util.List;

public class ValidateService {

    public static List<String> validateUser(Curso c) {
        List<String> errors = new ArrayList<>();
        if (c.getNombre() == null || c.getNombre().isEmpty()) {
            errors.add("El nombre es requerido");
        }

        if (c.getDuracion() == 0) {
            errors.add("La duracion es requerida");
        }
        return errors;
    }
}
