package com.example.demo.service;

import com.example.demo.models.Perfil;
import com.example.demo.models.PerfilDTO;
import com.example.demo.repository.CursosRepository;
import com.example.demo.repository.PerfilRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class PerfilService
{
    private final PerfilRepository perfilRepository;
    private final CursosRepository cursosRepository;
    private ModelMapper mm = new ModelMapper();

    @Autowired
    public PerfilService(PerfilRepository perfilRepository,
                         CursosRepository cursosRepository)
    {
        this.perfilRepository = perfilRepository;
        this.cursosRepository = cursosRepository;
    }

    public ResponseEntity<Perfil> addPerfil(Perfil perfil)
    {
        try
        {
            // Validar el objeto de entrada
            if (perfil == null)
            {
                return ResponseEntity.badRequest().build();
            }
            Perfil savedPerfil = perfilRepository.save(perfil);
            return ResponseEntity.ok(this.findByDni(savedPerfil.getDni()));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public Perfil findByDni(String dni)
    {
        return (Perfil) Arrays.stream(perfilRepository.findAll().toArray()).filter(p -> ((Perfil) p).getDni().equals(dni)).findFirst().orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Perfil no encontrado"));
    }

    public Perfil getPerfil(Integer id)
    {
        return perfilRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "El perfil con el identificador: " + id + " no encontrado."));
    }

    public ResponseEntity<?> deletePerfil(Integer id)
    {
        try
        {
            perfilRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }

    public ResponseEntity<?> updatePerfil(Integer id, Perfil perfil)
    {
        try{
            Perfil p = perfilRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "El perfil con el identificador: " + id + " no encontrado."));
            p.setDni(perfil.getDni());
            p.setApellido(perfil.getApellido());
            p.setNombre(perfil.getNombre());
            Perfil savedPerfil = perfilRepository.save(perfil);
            return ResponseEntity.ok(this.findByDni(savedPerfil.getDni()));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
