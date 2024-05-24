package br.com.una.academicShare.api.v1;

import br.com.una.academicShare.model.domain.Publicacao;
import br.com.una.academicShare.model.repository.PublicacaoRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/publicacoes")
public class PublicacaoResources  {

    @Autowired
    private PublicacaoRepositorio publicacaoRepositorio;

    @PostMapping
    public ResponseEntity<Publicacao> salvar(@RequestBody Publicacao publicacao) {
        Publicacao saved = publicacaoRepositorio.save(publicacao);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdPublicacao());
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicacao> alterar(@PathVariable("id") Long id,
                                           @RequestBody Publicacao Publicacao) {
        Optional<Publicacao> publicacaoDoBanco = publicacaoRepositorio.findById(id);
        if (!publicacaoDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        publicacaoDoBanco.get().update(id, publicacaoDoBanco.get());
        Publicacao saved = publicacaoRepositorio.save(Publicacao);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Publicacao>> get() {
        List<Publicacao> clienteList = publicacaoRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacao> getById(@PathVariable("id") Long id) {
        Optional<Publicacao> clienteList = publicacaoRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        publicacaoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

