package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Perfil
{
    private int id;
    private int dni;
    private String nombre;
    private String apellido;
}
