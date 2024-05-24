package br.com.una.academicShare.api.v1;

import br.com.una.academicShare.model.domain.Faculdade;
import br.com.una.academicShare.model.repository.FaculdadeRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/faculdades")
public class FaculdadeResources  {

    @Autowired
    private FaculdadeRepositorio faculdadeRepositorio;

    @PostMapping
    public ResponseEntity<Faculdade> salvar(@RequestBody Faculdade faculdade) {
        Faculdade saved = faculdadeRepositorio.save(faculdade);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdFaculdade());
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculdade> alterar(@PathVariable("id") Long id,
                                           @RequestBody Faculdade Faculdade) {
        Optional<Faculdade> faculdadeDoBanco = faculdadeRepositorio.findById(id);
        if (!faculdadeDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        faculdadeDoBanco.get().update(id, faculdadeDoBanco.get());
        Faculdade saved = faculdadeRepositorio.save(Faculdade);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Faculdade>> get() {
        List<Faculdade> clienteList = faculdadeRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculdade> getById(@PathVariable("id") Long id) {
        Optional<Faculdade> clienteList = faculdadeRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        faculdadeRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


