package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Perfil> perfiles;
}
