package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Entity
public class Perfil
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToMany
    @JoinTable(
            name="perfilesxcursos",
            joinColumns=
            @JoinColumn(name="perfilId"),
            inverseJoinColumns=
            @JoinColumn(name="cursoId"))
    private List<Curso> cursos;
    /*@NotNull
    @ManyToOne
    @JoinColumn(name = "cursoid")
    private Curso curso;*/
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni")
    private String dni;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "edad")
    private int edad;
}
