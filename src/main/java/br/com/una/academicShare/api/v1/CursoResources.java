package br.com.una.academicShare.api.v1;

import br.com.una.academicShare.Util.RestUtil;
import br.com.una.academicShare.model.domain.Curso;
import br.com.una.academicShare.model.repository.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoResources {

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @PostMapping
    public ResponseEntity<Curso> salvar(@RequestBody Curso curso) {
        Curso saved = cursoRepositorio.save(curso);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdCurso());
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> alterar(@PathVariable("id") Long id,
                                         @RequestBody Curso Curso) {
        Optional<Curso> cursoDoBanco = cursoRepositorio.findById(id);
        if (!cursoDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        cursoDoBanco.get().update(id, cursoDoBanco.get());
        Curso saved = cursoRepositorio.save(Curso);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> get() {
        List<Curso> clienteList = cursoRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable("id") Long id) {
        Optional<Curso> clienteList = cursoRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        cursoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


