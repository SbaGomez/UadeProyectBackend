package com.example.demo.service;

import com.example.demo.models.Curso;
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
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class CursosService {

    private final CursosRepository cursosRepository;
    private final PerfilRepository perfilRepository;
    private final ModelMapper mm = new ModelMapper();

    @Autowired
    public CursosService(CursosRepository cursosRepository,
                         PerfilRepository perfilRepository) {
        this.cursosRepository = cursosRepository;
        this.perfilRepository = perfilRepository;
    }

    public ResponseEntity<Curso> addCurso(Curso curso) {
        try {
            // Validar el objeto de entrada
            if (curso == null) {
                return ResponseEntity.badRequest().build();
            }
            Curso savedCurso = cursosRepository.save(curso);
            return ResponseEntity.ok(this.findById(savedCurso.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    public Curso findById(Integer id) {
        return cursosRepository.findAll().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "El curso con el identificador: " + id + " no encontrado."));
    }

    public Curso getCurso(Integer id) {
        return cursosRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "El curso con el identificador: " + id + " no encontrado"));
    }

    public ResponseEntity<?> deleteCurso(Integer id) {
        try {
            cursosRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }

    public ResponseEntity<?> updateCurso(Integer id, Curso curso) {
        try {
            Curso c = cursosRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Curso no encontrado"));
            c.setNombre(curso.getNombre());
            c.setDuracion(curso.getDuracion());
            cursosRepository.save(c);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<PerfilDTO> getCursoPerfil(Integer id) {
        try {
            List<Integer> perfilIdList = cursosRepository.findCursoPerfil(id);
            List<PerfilDTO> perfilDTOList = new ArrayList<>();
            for (Integer perfilId : perfilIdList) {
                Perfil perfil = perfilRepository.findById(perfilId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
                perfilDTOList.add(mm.map(perfil, PerfilDTO.class));
            }
            return perfilDTOList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}