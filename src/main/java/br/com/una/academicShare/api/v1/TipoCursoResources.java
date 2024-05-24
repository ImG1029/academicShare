package br.com.una.academicShare.api.v1;

import br.com.una.academicShare.model.domain.TipoCurso;
import br.com.una.academicShare.model.repository.TipoCursoRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tipocursos")
public class TipoCursoResources  {

    @Autowired
    private TipoCursoRepositorio tipoCursoRepositorio;

    @PostMapping
    public ResponseEntity<TipoCurso> salvar(@RequestBody TipoCurso tipoCurso) {
        TipoCurso saved = tipoCursoRepositorio.save(tipoCurso);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdTipoCurso());
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCurso> alterar(@PathVariable("id") Long id,
                                           @RequestBody TipoCurso tipoCurso) {
        Optional<TipoCurso> tipoCursoDoBanco = tipoCursoRepositorio.findById(id);
        if (!tipoCursoDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        tipoCursoDoBanco.get().update(id, tipoCursoDoBanco.get());
        TipoCurso saved = tipoCursoRepositorio.save(tipoCurso);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<TipoCurso>> get() {
        List<TipoCurso> clienteList = tipoCursoRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCurso> getById(@PathVariable("id") Long id) {
        Optional<TipoCurso> clienteList = tipoCursoRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        tipoCursoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
