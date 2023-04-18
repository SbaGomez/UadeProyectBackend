package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class PerfilDTO
{
    private String nombre;
    private String apellido;
    private String dni;
    private String sexo;
    private int edad;
}
