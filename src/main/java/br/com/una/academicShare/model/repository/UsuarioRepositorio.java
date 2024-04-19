package br.com.una.academicShare.model.repository;

import br.com.una.academicShare.model.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
