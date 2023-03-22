package com.example.demo.service;

import com.example.demo.model.Perfil;
import com.example.demo.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService
{
    private final PerfilRepository perfilRepository;
    List<Perfil> listPerfil;
    @Autowired
    public PerfilService(PerfilRepository perfilRepository)
    {
        this.perfilRepository = perfilRepository;
    }

    private void Add(Perfil perfil)
    {
        listPerfil.add(perfil);
    }

    public Perfil get(Integer id)
    {
        for(int x = 0; x < listPerfil.size(); x++)
        {
            Perfil p = listPerfil.get(x);
            if(p.getDni() == id)
            {
                return p;
            }
        }
        return null;
    }

    public void delete(Perfil perfil)
    {
        listPerfil.remove(perfil);
    }

    public void update(Perfil perfil)
    {
        for(int x = 0; x < listPerfil.size(); x++)
        {
            if(listPerfil.get(x).getDni() == perfil.getDni())
            {
                listPerfil.set(x,perfil);
            }
        }
    }
}
