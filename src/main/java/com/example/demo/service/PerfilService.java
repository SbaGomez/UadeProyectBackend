package com.example.demo.service;

import com.example.demo.models.Perfil;
import com.example.demo.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.*;

@Service
public class PerfilService
{
    //List<Perfil> listPerfil;

    private final PerfilRepository perfilRepository;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository)
    {
        this.perfilRepository = perfilRepository;
    }

    public Perfil addPerfil(Perfil perfil)
    {
        //listPerfil.add(perfil); //Trabajando con Lista
        try
        {
            perfilRepository.save(perfil);
            return this.findByDni(perfil.getDni());

        }
        catch (Exception e)
        {
            //return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
            return null;
        }
    }

    public Perfil findByDni(String dni)
    {
        return (Perfil) Arrays.stream(perfilRepository.findAll().toArray()).filter(p -> ((Perfil) p).getDni().equals(dni)).findFirst().orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Perfil no encontrado"));
    }

    public Perfil getPerfil(Integer id)
    {
        /*for(int x = 0; x < listPerfil.size(); x++)
        {
            Perfil p = listPerfil.get(x);
            if(p.getDni() == id)
            {
                return p;
            }
        }
        return null;*/

        return perfilRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Perfil no encontrado"));
    }

    public ResponseEntity<?> deletePerfil(Integer id)
    {
        //listPerfil.remove(perfil);  //Trabajando con Lista
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
        /*for(int x = 0; x < listPerfil.size(); x++)
        {
            if(listPerfil.get(x).getDni() == perfil.getDni())
            {
                listPerfil.set(x,perfil);
            }
        }*/

        try{
            Perfil p = perfilRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Perfil no encontrado"));
            p.setDni(perfil.getDni());
            p.setApellido(perfil.getApellido());
            p.setNombre(perfil.getNombre());
            perfilRepository.save(p);
            return ResponseEntity.status(OK).build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
