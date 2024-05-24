package br.com.una.academicShare.api.v1;

import br.com.una.academicShare.model.domain.Usuario;
import br.com.una.academicShare.model.repository.UsuarioRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/api/v1/usuarios")
    public class UsuarioResources  {

        @Autowired
        private UsuarioRepositorio usuarioRepositorio;

        @PostMapping
        public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario ) {
            Usuario saved = usuarioRepositorio.save(usuario);
            if (saved == null) {
                return ResponseEntity.noContent().build();
            }

            URI uri = RestUtil.getUri(saved.getIdUsuario());
            return ResponseEntity.created(uri).body(saved);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Usuario> alterar(@PathVariable("id") Long id,
                                               @RequestBody Usuario Usuario) {
            Optional<Usuario> usuarioDoBanco = usuarioRepositorio.findById(id);
            if (!usuarioDoBanco.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            usuarioDoBanco.get().update(id, usuarioDoBanco.get());
            Usuario saved = usuarioRepositorio.save(Usuario);
            if (saved == null) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(saved);
        }

        @GetMapping
        public ResponseEntity<List<Usuario>> get() {
            List<Usuario> clienteList = usuarioRepositorio.findAll();
            if (clienteList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clienteList);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Usuario> getById(@PathVariable("id") Long id) {
            Optional<Usuario> clienteList = usuarioRepositorio.findById(id);
            if (!clienteList.isPresent()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clienteList.get());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> remover(@PathVariable("id") Long id) {
            usuarioRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

