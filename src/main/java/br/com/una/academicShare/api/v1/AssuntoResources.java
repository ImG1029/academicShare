package br.com.una.academicShare.api.v1;

import br.com.una.academicShare.model.domain.Assunto;
import br.com.una.academicShare.model.repository.AssuntoRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/assuntos")
public class AssuntoResources {

    @Autowired
    private AssuntoRepositorio assuntoRepositorio;

    @PostMapping
    public ResponseEntity<Assunto> salvar(@RequestBody Assunto assunto) {
        Assunto saved = assuntoRepositorio.save(assunto);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdAssunto());
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assunto> alterar(@PathVariable("id") Long id,
                                           @RequestBody Assunto Assunto) {
        Optional<Assunto> assuntoDoBanco = assuntoRepositorio.findById(id);
        if (!assuntoDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        assuntoDoBanco.get().update(id, assuntoDoBanco.get());
        Assunto saved = assuntoRepositorio.save(Assunto);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Assunto>> get() {
        List<Assunto> clienteList = assuntoRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assunto> getById(@PathVariable("id") Long id) {
        Optional<Assunto> clienteList = assuntoRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        assuntoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

