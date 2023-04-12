package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Entity
public class Curso
{
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "duracion")
    private int duracion;
    @NotNull
    @ManyToMany
    @JoinTable(
            name="perfilesxcursos",
            joinColumns=
            @JoinColumn(name="cursoId"),
            inverseJoinColumns=
            @JoinColumn(name="perfilId"))
    private List<Perfil> perfiles;
    /*@OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Perfil> perfiles;*/
}
